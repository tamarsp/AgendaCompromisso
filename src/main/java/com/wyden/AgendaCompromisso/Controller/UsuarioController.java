package com.wyden.AgendaCompromisso.Controller;

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

/**
 * Controlador REST para a entidade Usuário.
 * Responsável por gerenciar operações como cadastro, login, busca,
 * atualização e exclusão de usuários.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowCredentials = "false")

public class UsuarioController {
	
	    @Autowired
	    private UsuarioRepository repository;
	    /**
	     * Busca um usuário pelo seu ID, omitindo a senha na resposta por segurança.
	     *
	     * @param id O ID do usuário a ser buscado.
	     * @return O objeto Usuario encontrado sem a senha (status 200 OK), ou status 404 Not Found se não existir.
	     */
	    @GetMapping("/{id}")
	    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
	        return repository.findById(id)
	            .map(usuario -> {
	                usuario.setSenha(null);
	                return ResponseEntity.ok(usuario);
	            })
	            .orElse(ResponseEntity.notFound().build());
	    }
	    
	    /**
	     * Realiza o cadastro de um novo usuário.
	     * Verifica se o email já está em uso antes de salvar. A senha é omitida na resposta de sucesso.
	     *
	     * @param dto O objeto UsuarioDtoCadastro contendo nome, email e senha do novo usuário.
	     * @return O objeto Usuario salvo (status 200 OK) ou status 400 Bad Request se o email já estiver em uso.
	     */

	    @PostMapping("/cadastro")
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
	    
	    /**
	     * Realiza a autenticação (login) do usuário verificando email e senha.
	     *
	     * @param dto O objeto UsuarioDtoLogin contendo o email e a senha para autenticação.
	     * @return O objeto Usuario autenticado sem a senha (status 200 OK), ou status 401 Unauthorized se as credenciais forem inválidas.
	     */
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody UsuarioDtoLogin dto) {

	        var user = repository.findByEmail(dto.getEmail());

	        if (user.isPresent() && user.get().getSenha().equals(dto.getSenha())) {
	            Usuario u = user.get();
	            u.setSenha(null);
	            return ResponseEntity.ok(u);
	        }

	        return ResponseEntity.status(401).body("Email ou senha inválidos");
	    }

	    /**
	     * Atualiza os dados de um usuário existente.
	     *
	     * @param id O ID do usuário a ser atualizado.
	     * @param usuario O objeto Usuario com os novos dados (nome, email e senha).
	     * @return O Usuario atualizado (status 200 OK) ou status 404 Not Found se o usuário não existir.
	     */
	    @PutMapping("/{id}")
	    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
	        return repository.findById(id)
	            .map(existente -> {
	                existente.setNome(usuario.getNome());
	                existente.setEmail(usuario.getEmail());
	                existente.setSenha(usuario.getSenha());
	                return ResponseEntity.ok(repository.save(existente));
	            })
	            .orElse(ResponseEntity.notFound().build());
	    }
	    
	    /**
	     * Deleta um usuário pelo seu ID.
	     *
	     * @param id O ID do usuário a ser deletado.
	     * @return Status 200 OK se o usuário for deletado com sucesso, ou status 404 Not Found se não existir.
	     */
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
