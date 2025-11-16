package com.wyden.AgendaCompromisso.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wyden.AgendaCompromisso.Entities.Compromisso;

public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
	/**
	 * Criação do metodo
	 * @param usuarioId
	 * @return
	 */

	List<Compromisso> findByUsuarioId(Long usuarioId);

}