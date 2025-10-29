package Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entities.Compromisso;
import Entities.Usuario;
import Repository.CompromissoRepository;

@Service
public class CompromissoService {
	@Autowired
	private final CompromissoRepository repository;
	
	public CompromissoService(CompromissoRepository repository) {
		this.repository = repository;		
	}
	
	/**
	 * 
	 */
	public Compromisso Salvar(Compromisso compromisso, Usuario usuario) {
		compromisso.setUsuario(usuario);
		return repository.save(compromisso);		
	}
	
	public List<Compromisso> listarTodos(){
		return repository.findAll();
	}
	
	public List<Compromisso> listarPorUsuario(Long usuarioId){
		return repository.findByUsuarioId(usuarioId);
	}
	
	public Optional<Compromisso> buscarPorId(Long id) {
        return repository.findById(id);
    }
	
	public boolean deletar(Long id) {
	    if (repository.existsById(id)) {
	        repository.deleteById(id);
	        return true;
	    }
	    return false;
	}
	
	public Compromisso atualizar(Long id, Compromisso novoCompromisso) {
		Optional<Compromisso> existeOpt = buscarPorId(id);
		if (existeOpt.isPresent()) {
		    Compromisso existe = existeOpt.get();
		    existe.setTitulo(novoCompromisso.getTitulo());
		    existe.setDescricao(novoCompromisso.getDescricao());
		    existe.setDataHora(novoCompromisso.getDataHora());
		    return repository.save(existe);
		}
		return null;		
	}	
}
