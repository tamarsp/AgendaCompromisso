package Service;

import java.util.List;

import Entities.Compromisso;
import Entities.Usuario;
import Repository.CompromissoRepository;

public class CompromissoService {
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
	
	public Compromisso buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
	
	public Compromisso atualizar(Long id, Compromisso novoCompromisso) {
		Compromisso existe = buscarPorId(id);
		if(existe != null) {
			existe.setTitulo(novoCompromisso.getTitulo());
			existe.setDescricao(novoCompromisso.getDescricao());
			existe.setDataHora(novoCompromisso.getDataHora());
			return repository.save(existe);
		}
		return null;
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
}
