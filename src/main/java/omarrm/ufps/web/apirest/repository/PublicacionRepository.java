package omarrm.ufps.web.apirest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import omarrm.ufps.web.apirest.entity.Publicacion;
import omarrm.ufps.web.apirest.entity.Usuario;

public interface PublicacionRepository extends CrudRepository<Publicacion, Integer> {

	public List<Publicacion> findByUsuarioOrderByFechaPublicacionDesc(Usuario usuario);
	
}
