package com.wyden.AgendaCompromisso.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.AgendaCompromisso.Entities.Compromisso;
import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.CompromissoRepository;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CompromissoService {
	@Autowired
    private CompromissoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

  
    public List<Compromisso> listarTodos() {
        return repository.findAll();
    }

    public Compromisso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));
    }


    @Transactional
    public Compromisso salvar(Compromisso compromisso) {
        return repository.save(compromisso);
    }

    
    @Transactional
    public Compromisso salvarComUsuario(Compromisso compromisso, Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        compromisso.setUsuario(usuario);

        return repository.save(compromisso);
    }

    @Transactional
    public Compromisso atualizar(Long id, Compromisso dados) {

        Compromisso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));

        existente.setTitulo(dados.getTitulo());
        existente.setDescricao(dados.getDescricao());
        existente.setDataHora(dados.getDataHora());

        // Se vier usuário no JSON, atualiza
        if (dados.getUsuario() != null) {
            existente.setUsuario(dados.getUsuario());
        }

        return repository.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        Compromisso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));

        repository.delete(existente);
    }

}
