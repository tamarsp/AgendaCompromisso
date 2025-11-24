package com.wyden.AgendaCompromisso.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.AgendaCompromisso.Entities.Usuario;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;
/**
 * Serviço responsável pela lógica de negócios relacionada a usuários.
 * Este serviço realiza operações como listar, buscar, criar, atualizar e deletar usuários,
 * gerenciando a interação com o repositório.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    /**
     * Lista todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de todos os objetos Usuario.
     */
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
    /**
     * Busca um usuário pelo seu ID único.
     *
     * @param id O ID do usuário a ser buscado.
     * @return O objeto Usuario encontrado.
     * @throws RuntimeException Se o usuário com o ID especificado não for encontrado.
     */
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    /**
     * Salva um novo usuário no banco de dados.
     *
     * @param usuario O objeto Usuario a ser salvo.
     * @return O objeto Usuario salvo, incluindo o ID gerado.
     */
    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }
    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param id O ID do usuário a ser atualizado.
     * @param dadosAtualizados O objeto Usuario contendo os novos dados (nome, email, senha).
     * @return O objeto Usuario atualizado.
     * @throws RuntimeException Se o usuário com o ID especificado não for encontrado.
     */
    @Transactional
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dadosAtualizados.getNome());
        usuario.setEmail(dadosAtualizados.getEmail());
        usuario.setSenha(dadosAtualizados.getSenha());

        return repository.save(usuario);
    }
    /**
     * Deleta um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser deletado.
     * @throws RuntimeException Se o usuário com o ID especificado não for encontrado.
     */
    @Transactional
    public void deletar(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(usuario);
    }

}	
