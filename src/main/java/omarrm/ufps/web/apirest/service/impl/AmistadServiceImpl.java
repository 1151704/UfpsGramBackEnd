package omarrm.ufps.web.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omarrm.ufps.web.apirest.entity.Amistad;
import omarrm.ufps.web.apirest.entity.AmistadEstado;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.repository.AmistadRepository;
import omarrm.ufps.web.apirest.service.AmistadService;

@Service
public class AmistadServiceImpl implements AmistadService {

	@Autowired
	private AmistadRepository repository;

	@Override
	public List<Amistad> findByEstadoAndUsuario(AmistadEstado estado, Usuario usuario) {
		return repository.findByEstadoAndUsuario(estado, usuario);
	}

	@Override
	public List<Amistad> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

	@Override
	public List<Amistad> findByEstadoAndAmistad(AmistadEstado estado, Usuario amistad) {
		return repository.findByAmistad(amistad);
	}

	@Override
	public List<Amistad> findByAmistad(Usuario amistad) {
		return repository.findByAmistad(amistad);
	}

	@Override
	public Amistad save(Amistad amistad) {
		return repository.save(amistad);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Amistad findByUsuarioAndAmistad(Usuario usuario, Usuario amistad) {
		return repository.findByUsuarioAndAmistad(usuario, amistad);
	}

}
