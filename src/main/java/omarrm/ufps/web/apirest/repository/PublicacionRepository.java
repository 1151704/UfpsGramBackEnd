package omarrm.ufps.web.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import omarrm.ufps.web.apirest.entity.Publicacion;
import omarrm.ufps.web.apirest.entity.Usuario;

public interface PublicacionRepository extends CrudRepository<Publicacion, Integer> {

	public List<Publicacion> findByUsuarioOrderByFechaPublicacionDesc(Usuario usuario);
	
	String query = "select p from Publicacion p " + 
			"join Usuario u on u = p.usuario " + 
			"join Amistad a on (a.amistad = u and a.estado ='ACEPTADA') " + 
			"where a.usuario = :usuario " + 
			"order by p.fechaPublicacion desc";
	
	@Query(query)
	public List<Publicacion> findByAmigos(@Param("usuario") Usuario Usuario);
	
}
