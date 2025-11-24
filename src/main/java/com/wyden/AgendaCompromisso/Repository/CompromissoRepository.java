package com.wyden.AgendaCompromisso.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wyden.AgendaCompromisso.Entities.Compromisso;

/**
 * Repositório JPA para a entidade {@link Compromisso}.
 * Estende {@link JpaRepository} para fornecer métodos padrão de CRUD
 * e define métodos customizados para consulta de compromissos.
 */
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
	/**
     * Retorna uma lista de compromissos associados a um usuário específico através do ID do usuário.
     *
     * @param usuarioId O ID do usuário cujos compromissos serão retornados.
     * @return Uma lista de objetos {@link Compromisso} pertencentes ao usuário.
     */
	List<Compromisso> findByUsuarioId(Long usuarioId);

}