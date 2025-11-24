package com.wyden.AgendaCompromisso.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wyden.AgendaCompromisso.Entities.Usuario;

/**
 * Repositório JPA para a entidade {@link Usuario}.
 * Fornece métodos padrão de CRUD e também métodos customizados para consulta de usuários.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long >{
	/**
	 * Busca um usuário pelo seu endereço de e-mail.
	 *
	 * @param email O e-mail do usuário a ser buscado.
	 * @return Um {@link Optional} contendo o objeto Usuario, se encontrado, ou um Optional vazio, caso contrário.
	 */
	Optional<Usuario> findByEmail(String email);
	
}