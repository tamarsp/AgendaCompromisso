package Service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entities.Usuario;
import Repository.UsuarioRepository;
@Service
public class UsuarioService {
	@Autowired
	private final UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public Usuario cadastro(Usuario usuario) {
		return repository.save(usuario);
	}
	//presica mudar, talvez
	public Optional<Usuario>buscarPorEmail(String email) {
		return null;
	}

	
	public Optional<Usuario> login(String email, String senha){
		Optional<Usuario> usuarioOpt = repository.findByEmail(email);
		if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            return usuarioOpt;
        }
		return Optional.empty();
	}

}
