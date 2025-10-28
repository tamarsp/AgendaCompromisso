package Service;

import javax.swing.text.html.Option;

import Entities.Usuario;
import Repository.UsuarioRepository;

public class UsuarioService {
	private final UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public Usuario cadastro(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public Option<Usuario> login(String email, String senha){
		Option<Usuario> usuarioOpt = repository.findByEmail(email);
		if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            return usuarioOpt;
        }
		return Optional.empty;
	}
}
