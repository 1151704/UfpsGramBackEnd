package omarrm.ufps.web.apirest.repository;

import org.springframework.data.repository.CrudRepository;

import omarrm.ufps.web.apirest.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByUsername(String username);
	
}
