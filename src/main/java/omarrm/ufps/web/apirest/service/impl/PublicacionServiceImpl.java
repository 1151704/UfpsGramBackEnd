package omarrm.ufps.web.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omarrm.ufps.web.apirest.entity.Publicacion;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.repository.PublicacionRepository;
import omarrm.ufps.web.apirest.service.PublicacionService;

@Service
public class PublicacionServiceImpl implements PublicacionService {

	@Autowired
	private PublicacionRepository repository;
	
	@Override
	public List<Publicacion> findByUsuario(Usuario usuario) {
		return repository.findByUsuarioOrderByFechaPublicacionDesc(usuario);
	}

	@Override
	public Publicacion save(Publicacion publicacion) {
		return repository.save(publicacion);
	}

	@Override
	public Publicacion findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
