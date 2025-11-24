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
 * Controlador de compromisso responsavel por listar, criar, deletar e atualizar
 * cpmpromissos dos usuario.
 * */

@RestController
@RequestMapping("/compromissos")
@CrossOrigin(origins = "*")
public class CompromissoController {

    @Autowired
    private CompromissoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
/**
 * LISTAR COMPROMISSOS DE UM USUÁRIO
 *usuarioId
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
 *  BUSCAR POR ID
 */
    
    @GetMapping("/{id}")
    public ResponseEntity<Compromisso> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(comp -> ResponseEntity.ok(comp))
                .orElse(ResponseEntity.notFound().build());
    }
    
/**
 * CRIAR COMPROMISSO PARA USUÁRIO ESPECÍFICO
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
 * ATUALIZA O COMPROMISSO
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
     * DELETAR, APAGA O COMPROMISSO
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