package omarrm.ufps.web.apirest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import omarrm.ufps.web.apirest.entity.Amistad;
import omarrm.ufps.web.apirest.entity.AmistadEstado;
import omarrm.ufps.web.apirest.entity.Usuario;

public interface AmistadRepository extends CrudRepository<Amistad, Integer> {

	public List<Amistad> findByEstadoAndUsuario(AmistadEstado estado, Usuario usuario);
	
	public List<Amistad> findByUsuario(Usuario usuario);
	
	public List<Amistad> findByEstadoAndAmistad(AmistadEstado estado, Usuario amistado);
	
	public List<Amistad> findByAmistad(Usuario amistad);
	
}
