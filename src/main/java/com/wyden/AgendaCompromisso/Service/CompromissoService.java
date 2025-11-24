package com.wyden.AgendaCompromisso.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.AgendaCompromisso.Entities.Compromisso;
import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.CompromissoRepository;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

/**
* Serviço responsável pela lógica de negócios relacionada a compromissos.
* Este serviço realiza operações como listar, buscar, criar, atualizar e deletar compromissos.
* Também permite associar compromissos a usuários.
*/

@Service
public class CompromissoService {
	@Autowired
    private CompromissoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

   /**
    * Retorna todos os compromissos do sistema.
    * 
    * lista de todos os compromissos
    */
    public List<Compromisso> listarTodos() {
        return repository.findAll();
    }
    /**
     * Busca um compromisso pelo seu ID.
     * 
     *  id ID do compromisso
     *  compromisso encontrado
     *  RuntimeException se o compromisso não for encontrado
     */
    public Compromisso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));
    }

   /**
    * Salva um novo compromisso no banco de dados.
    */
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
    /**
    * Deleta um compromisso no banco de dados.
    */ 
    @Transactional
    public void deletar(Long id) {
        Compromisso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));

        repository.delete(existente);
    }

}
