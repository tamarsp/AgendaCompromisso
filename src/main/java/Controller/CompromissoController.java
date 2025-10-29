package Controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entities.Compromisso;
import Entities.Usuario;
import Service.CompromissoService;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {
	@Autowired
	private CompromissoService service;
	
	 @GetMapping
	    public List<Compromisso> listarTodos() {
	        return service.listarTodos();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Compromisso> buscarPorId(@PathVariable Long id) {
	        Optional<Compromisso> compromisso = service.buscarPorId(id);
	        return compromisso.map(ResponseEntity::ok)
	                          .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Compromisso criar(@RequestBody Compromisso compromisso) {
	        Usuario usuario = compromisso.getUsuario(); // ou buscar pelo id do usuário
	        return service.Salvar(compromisso, usuario);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Compromisso> atualizar(@PathVariable Long id, @RequestBody Compromisso compromissoAtualizado) {
	        Compromisso atualizado = service.atualizar(id, compromissoAtualizado);
	        if (atualizado != null) {
	            return ResponseEntity.ok(atualizado);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletar(@PathVariable Long id) {
	        service.deletar(id);
	        return ResponseEntity.noContent().build();
	    }
	
}
