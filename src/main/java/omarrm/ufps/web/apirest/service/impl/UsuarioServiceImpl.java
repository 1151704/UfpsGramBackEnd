package omarrm.ufps.web.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.repository.UsuarioRepository;
import omarrm.ufps.web.apirest.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Usuario findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) repository.findAll();
	}

	@Override
	public List<Usuario> findByFilter(String filter) {
		return (List<Usuario>) repository.findDistinctUsuarioByUsernameLikeOrNombreLike(filter, filter);
	}

}
