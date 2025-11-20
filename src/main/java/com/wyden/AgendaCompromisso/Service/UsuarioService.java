package com.wyden.AgendaCompromisso.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dadosAtualizados.getNome());
        usuario.setEmail(dadosAtualizados.getEmail());
        usuario.setSenha(dadosAtualizados.getSenha());

        return repository.save(usuario);
    }

    @Transactional
    public void deletar(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(usuario);
    }

}	
