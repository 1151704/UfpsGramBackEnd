package omarrm.ufps.web.apirest.service;

import java.util.List;

import omarrm.ufps.web.apirest.entity.Amistad;
import omarrm.ufps.web.apirest.entity.AmistadEstado;
import omarrm.ufps.web.apirest.entity.Usuario;

public interface AmistadService {

	public List<Amistad> findByEstadoAndUsuario(AmistadEstado estado, Usuario usuario);
	
	public List<Amistad> findByUsuario(Usuario usuario);
	
	public Amistad findByUsuarioAndAmistad(Usuario usuario, Usuario amistad);
	
	public List<Amistad> findByEstadoAndAmistad(AmistadEstado estado, Usuario amistado);
	
	public List<Amistad> findByAmistad(Usuario amistad);
	
	public Amistad save(Amistad amistad);
	
	public void deleteById(Integer id);
	
}
