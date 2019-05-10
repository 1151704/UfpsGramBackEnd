package omarrm.ufps.web.apirest.service;

import java.util.List;

import omarrm.ufps.web.apirest.entity.Publicacion;
import omarrm.ufps.web.apirest.entity.Usuario;

public interface PublicacionService {
	
	public List<Publicacion> findByUsuario(Usuario usuario);
	
	public List<Publicacion> findByAmigos(Usuario usuario);
	
	public Publicacion save(Publicacion publicacion);
	
	public Publicacion findById(Integer id);
	
	public void deleteById(Integer id);

}
