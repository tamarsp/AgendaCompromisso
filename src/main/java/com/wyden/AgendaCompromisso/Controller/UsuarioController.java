package com.wyden.AgendaCompromisso.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;
import com.wyden.AgendaCompromisso.dto.UsuarioDtoCadastro;
import com.wyden.AgendaCompromisso.dto.UsuarioDtoLogin;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowCredentials = "false")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	/**
	 * @param
	 * get e post 
	 * @return
	 */
	@GetMapping
	public List<Usuario> listarTodos(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
		return repository.findById(id)
				.map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Usuario criar(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}
	
	@PostMapping("/usuarios/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioDtoCadastro dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já está em uso");
        }

        Usuario novo = new Usuario();
        novo.setNome(dto.getNome());
        novo.setEmail(dto.getEmail());
        novo.setSenha(dto.getSenha());

        Usuario salvo = repository.save(novo);
        salvo.setSenha(null); 

        return ResponseEntity.ok(salvo);
    }
	
	@PostMapping("/usuarios/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDtoLogin dto) {

        var user = repository.findByEmail(dto.getEmail());

        if (user.isPresent() && user.get().getSenha().equals(dto.getSenha())) {
            Usuario u = user.get();
            u.setSenha(null); 
            return ResponseEntity.ok(u);
        }

        return ResponseEntity.status(401).body("Email ou senha inválidos");
    }

	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
		return repository.findById(id)
                .map(existente -> {
                    existente.setNome(usuario.getNome());
                    existente.setEmail(usuario.getEmail());
                    existente.setSenha(usuario.getSenha());
                    return ResponseEntity.ok(repository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(existente -> {
                    repository.delete(existente);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}