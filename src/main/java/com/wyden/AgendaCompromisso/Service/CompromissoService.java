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
* Este serviço realiza operações como listar, buscar, criar, atualizar e deletar compromissos,
* gerenciando a interação com os repositórios de Compromisso e Usuário.
*/

@Service
public class CompromissoService {
	@Autowired
    private CompromissoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Retorna todos os compromissos cadastrados no sistema.
     *
     * @return Uma lista de todos os objetos Compromisso.
     */
    public List<Compromisso> listarTodos() {
        return repository.findAll();
    }
    /**
     * Busca um compromisso pelo seu ID único.
     * * @param id O ID do compromisso a ser buscado.
     * @return O objeto Compromisso encontrado.
     * @throws RuntimeException Se o compromisso com o ID especificado não for encontrado.
     */
    public Compromisso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));
    }

    /**
     * Salva um novo compromisso no banco de dados.
     *
     * @param compromisso O objeto Compromisso a ser salvo.
     * @return O objeto Compromisso salvo, incluindo o ID gerado.
     */
    @Transactional
    public Compromisso salvar(Compromisso compromisso) {
        return repository.save(compromisso);
    }

    /**
     * Salva um novo compromisso associando-o a um usuário existente.
     * * @param compromisso O objeto Compromisso a ser salvo.
     * @param usuarioId O ID do usuário ao qual o compromisso será associado.
     * @return O objeto Compromisso salvo.
     * @throws RuntimeException Se o usuário com o ID especificado não for encontrado.
     */
    @Transactional
    public Compromisso salvarComUsuario(Compromisso compromisso, Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        compromisso.setUsuario(usuario);

        return repository.save(compromisso);
    }
    /**
     * Atualiza os dados de um compromisso existente.
     * * @param id O ID do compromisso a ser atualizado.
     * @param dados O objeto Compromisso contendo os novos dados (título, descrição, dataHora).
     * @return O objeto Compromisso atualizado.
     * @throws RuntimeException Se o compromisso com o ID especificado não for encontrado.
     */
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
     * Deleta um compromisso pelo seu ID.
     * * @param id O ID do compromisso a ser deletado.
     * @throws RuntimeException Se o compromisso com o ID especificado não for encontrado.
     */ 
    @Transactional
    public void deletar(Long id) {
        Compromisso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));

        repository.delete(existente);
    }

}
