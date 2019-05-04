package omarrm.ufps.web.apirest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import omarrm.ufps.web.apirest.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Usuario findByUsername(String username);
	
	public List<Usuario> findDistinctUsuarioByUsernameLikeOrNombreLike(String username, String nombre);
	
}
