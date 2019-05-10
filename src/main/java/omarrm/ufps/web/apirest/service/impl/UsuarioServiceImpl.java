package omarrm.ufps.web.apirest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omarrm.ufps.web.apirest.entity.Amistad;
import omarrm.ufps.web.apirest.entity.AmistadEstado;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.model.UsuarioApi;
import omarrm.ufps.web.apirest.repository.AmistadRepository;
import omarrm.ufps.web.apirest.repository.UsuarioRepository;
import omarrm.ufps.web.apirest.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private AmistadRepository amistad;

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

	@Override
	public List<UsuarioApi> busqueda(String filter, Usuario actual) {
		List<Usuario> usuarios = this.findByFilter(filter);

		if (usuarios != null) {
			List<UsuarioApi> apis = new ArrayList<UsuarioApi>();

			for (Usuario usuario : usuarios) {
				if (!usuario.equals(actual)) {
					apis.add(informacion(usuario, actual));
				}
			}

			return apis;

		}

		return null;
	}

	private UsuarioApi informacion(Usuario usuario, Usuario actual) {

		UsuarioApi api = new UsuarioApi();

		api.setUsuario(usuario);

		api.setPendiente(amistad.existsByUsuarioAndAmistadAndEstado(actual, usuario, AmistadEstado.ENVIADA));
		api.setSiguiendo(amistad.existsByUsuarioAndAmistadAndEstado(actual, usuario, AmistadEstado.ACEPTADA));
		api.setSeguidor(amistad.existsByUsuarioAndAmistadAndEstado(usuario, actual, AmistadEstado.ACEPTADA));
		api.setAceptar(amistad.existsByUsuarioAndAmistadAndEstado(usuario, actual, AmistadEstado.ENVIADA));

		return api;
	}

	@Override
	public List<UsuarioApi> seguidores(Usuario actual) {

		List<Amistad> seguidores = amistad.findByEstadoAndAmistad(AmistadEstado.ACEPTADA, actual);

		if (seguidores != null) {
			List<UsuarioApi> apis = new ArrayList<UsuarioApi>();
			for (Amistad a : seguidores) {
				apis.add(informacion(a.getUsuario(), actual));
			}
			return apis;
		}

		return new ArrayList<>();
	}

	@Override
	public List<UsuarioApi> siguiendo(Usuario actual) {

		List<Amistad> siguiendo = amistad.findByEstadoAndUsuario(AmistadEstado.ACEPTADA, actual);

		if (siguiendo != null) {
			List<UsuarioApi> apis = new ArrayList<UsuarioApi>();
			for (Amistad a : siguiendo) {
				apis.add(informacion(a.getAmistad(), actual));
			}
			return apis;
		}

		return new ArrayList<>();
	}

	@Override
	public List<UsuarioApi> solicitudesRecibidas(Usuario actual) {
		
		List<Amistad> solicitudes = amistad.findByEstadoAndAmistad(AmistadEstado.ENVIADA, actual);

		if (solicitudes != null) {
			List<UsuarioApi> apis = new ArrayList<UsuarioApi>();
			for (Amistad a : solicitudes) {
				apis.add(informacion(a.getUsuario(), actual));
			}
			return apis;
		}
		
		return new ArrayList<>();
	}

	@Override
	public List<UsuarioApi> solicitudesEnviadas(Usuario actual) {
		
		List<Amistad> solicitudes = amistad.findByEstadoAndUsuario(AmistadEstado.ENVIADA, actual);

		if (solicitudes != null) {
			List<UsuarioApi> apis = new ArrayList<UsuarioApi>();
			for (Amistad a : solicitudes) {
				apis.add(informacion(a.getAmistad(), actual));
			}
			return apis;
		}
		
		return new ArrayList<>();
	}

	@Override
	public long seguidoresTotal(Usuario actual) {
		return amistad.countByEstadoAndAmistad(AmistadEstado.ACEPTADA, actual);
	}

	@Override
	public long siguiendoTotal(Usuario actual) {
		return amistad.countByEstadoAndUsuario(AmistadEstado.ACEPTADA, actual);
	}

}
