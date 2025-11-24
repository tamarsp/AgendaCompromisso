package com.wyden.AgendaCompromisso.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyden.AgendaCompromisso.Entities.Compromisso;
import com.wyden.AgendaCompromisso.Repository.CompromissoRepository;
import com.wyden.AgendaCompromisso.Repository.UsuarioRepository;

/**
 * Controlador REST para a entidade Compromisso.
 * Responsável por gerenciar as operações CRUD (Criar, Listar, Buscar, Atualizar e Deletar)
 * de compromissos associados a usuários.
 */

@RestController
@RequestMapping("/compromissos")
@CrossOrigin(origins = "*")
public class CompromissoController {

    @Autowired
    private CompromissoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Lista todos os compromissos associados a um usuário específico.
     *
     * @param usuarioId O ID do usuário cujos compromissos devem ser listados.
     * @return Uma lista de Compromissos (status 200 OK) ou status 404 Not Found se o usuário não existir.
     */     
     
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Compromisso>> listarPorUsuario(@PathVariable Long usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.notFound().build();
        }

        List<Compromisso> lista = repository.findByUsuarioId(usuarioId);

        return ResponseEntity.ok(lista);
    }
    
    /**
     * Busca um compromisso pelo seu ID.
     *
     * @param id O ID do compromisso a ser buscado.
     * @return O Compromisso encontrado (status 200 OK) ou status 404 Not Found se não existir.
     */
    
    @GetMapping("/{id}")
    public ResponseEntity<Compromisso> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(comp -> ResponseEntity.ok(comp))
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Cria um novo compromisso e o associa a um usuário existente.
     *
     * @param usuarioId O ID do usuário ao qual o compromisso será associado.
     * @param compromisso O objeto Compromisso contendo os dados a serem salvos.
     * @return O Compromisso salvo (status 200 OK) ou status 404 Not Found se o usuário não existir.
     */
    
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> criar(@PathVariable Long usuarioId, @RequestBody Compromisso compromisso) {

        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    compromisso.setUsuario(usuario);
                    Compromisso salvo = repository.save(compromisso);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Atualiza um compromisso existente.
     *
     * @param id O ID do compromisso a ser atualizado.
     * @param compromisso O objeto Compromisso com os novos dados.
     * @return O Compromisso atualizado (status 200 OK) ou status 404 Not Found se o compromisso não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Compromisso> atualizar(@PathVariable Long id, @RequestBody Compromisso compromisso) {

        return repository.findById(id)
                .map(existente -> {
                    existente.setTitulo(compromisso.getTitulo());
                    existente.setDescricao(compromisso.getDescricao());
                    existente.setDataHora(compromisso.getDataHora());
                    return ResponseEntity.ok(repository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deleta um compromisso pelo seu ID.
     *
     * @param id O ID do compromisso a ser deletado.
     * @return Status 204 No Content se o compromisso for deletado com sucesso, ou status 404 Not Found se não existir.
     */   
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}