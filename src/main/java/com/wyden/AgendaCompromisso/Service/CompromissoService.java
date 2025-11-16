package com.wyden.AgendaCompromisso.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.AgendaCompromisso.Entities.Compromisso;
import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.CompromissoRepository;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

@Service
public class CompromissoService {
	 @Autowired
	    private CompromissoRepository repository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    public List<Compromisso> listarTodos() {
	        return repository.findAll();
	    }
	    
	    public Optional<Compromisso> buscarPorId(Long id) {
	        return repository.findById(id);
	    }

	    public Compromisso salvar(Compromisso compromisso) {
	        return repository.save(compromisso);
	    }

	    public Compromisso salvarComUsuario(Compromisso compromisso, Long usuarioId) {
	        Usuario usuario = usuarioRepository.findById(usuarioId)
	                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	        compromisso.setUsuario(usuario);

	        return repository.save(compromisso);
	    }
	    
	    public Compromisso atualizar(Long id, Compromisso dadosAtualizados) {
	        return repository.findById(id)
	                .map(compromisso -> {
	                    compromisso.setTitulo(dadosAtualizados.getTitulo());
	                    compromisso.setDescricao(dadosAtualizados.getDescricao());
	                    compromisso.setDataHora(dadosAtualizados.getDataHora());
	                    compromisso.setUsuario(dadosAtualizados.getUsuario());
	                    return repository.save(compromisso);
	                })
	                .orElse(null);
	    }

	    public boolean deletar(Long id) {
	        return repository.findById(id)
	                .map(compromisso -> {
	                    repository.delete(compromisso);
	                    return true;
	                })
	                .orElse(false);
	    }

}
