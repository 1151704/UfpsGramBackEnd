package omarrm.ufps.web.apirest.service;

import java.util.List;

import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.model.UsuarioApi;

public interface UsuarioService {

	public Usuario save(Usuario usuario);

	public void deleteById(Integer id);

	public Usuario findById(Integer id);

	public Usuario findByUsername(String username);

	public List<Usuario> findAll();

	public List<Usuario> findByFilter(String filter);

	public List<UsuarioApi> seguidores(Usuario actual);

	public List<UsuarioApi> siguiendo(Usuario actual);

	public List<UsuarioApi> busqueda(String filter, Usuario actual);
	
	public List<UsuarioApi> solicitudesRecibidas(Usuario actual);
	
	public List<UsuarioApi> solicitudesEnviadas(Usuario actual);

}
