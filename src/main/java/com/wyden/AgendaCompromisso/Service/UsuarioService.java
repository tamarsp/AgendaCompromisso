package com.wyden.AgendaCompromisso.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario>listarTodos(){
		return repository.findAll();
	}
	
	 public Optional<Usuario> buscarPorId(Long id) {
	        return repository.findById(id);
	    }

	    public Usuario salvar(Usuario usuario) {
	        return repository.save(usuario);
	    }
	    
	 public Usuario atualizar(Long id, Usuario dadosAtualizados) {
	        return repository.findById(id)
	                .map(usuario -> {
	                    usuario.setNome(dadosAtualizados.getNome());
	                    usuario.setEmail(dadosAtualizados.getEmail());
	                    usuario.setSenha(dadosAtualizados.getSenha());
	                    return repository.save(usuario);
	                })
	                .orElse(null);
	    }
	 
	 public boolean deletar(Long id) {
	        return repository.findById(id)
	                .map(usuario -> {
	                    repository.delete(usuario);
	                    return true;
	                })
	                .orElse(false);
	    }
}	
