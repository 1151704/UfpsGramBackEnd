package omarrm.ufps.web.apirest.service;

import java.util.List;

import omarrm.ufps.web.apirest.entity.Usuario;

public interface UsuarioService {

	public Usuario save(Usuario usuario);

	public void deleteById(Integer id);

	public Usuario findById(Integer id);

	public Usuario findByUsername(String username);

	public List<Usuario> findAll();
	
	public List<Usuario> findByFilter(String filter);

}
