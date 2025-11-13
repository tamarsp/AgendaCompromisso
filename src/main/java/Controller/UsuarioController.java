package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entities.Usuario;
import Service.UsuarioService;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		Usuario novo =service.salvar(usuario);
		return ResponseEntity.ok(novo);
	}
	
	@GetMapping("/buscar/{email}")
	public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email){
		return service.buscarPorEmail(email)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}
