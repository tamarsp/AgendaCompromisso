package com.wyden.AgendaCompromisso.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wyden.AgendaCompromisso.Entities.Compromisso;

/**
 * Repositório JPA para a entidade {@link Compromisso}.
 * Fornece métodos padrão de CRUD e também métodos customizados para consulta de compromissos.
 *>Extende que já disponibiliza operações como salvar, atualizar, deletar e buscar por ID.</p>
 *  */
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
	/**
     * Retorna uma lista de compromissos associados a um usuário específico.
     * usuarioId ID do usuário cujos compromissos serão retornados
     * lista de compromissos do usuário
     */
	List<Compromisso> findByUsuarioId(Long usuarioId);

}