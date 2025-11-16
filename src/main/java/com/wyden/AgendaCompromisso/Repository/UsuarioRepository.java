package com.wyden.AgendaCompromisso.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wyden.AgendaCompromisso.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long >{

	Optional<Usuario> findByEmail(String email);
	
}