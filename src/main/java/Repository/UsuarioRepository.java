package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long >{
	
}
