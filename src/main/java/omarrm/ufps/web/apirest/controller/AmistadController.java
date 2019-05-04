package omarrm.ufps.web.apirest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omarrm.ufps.web.apirest.entity.Amistad;
import omarrm.ufps.web.apirest.entity.AmistadEstado;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.service.AmistadService;
import omarrm.ufps.web.apirest.service.UsuarioService;

@RestController
@RequestMapping("amistad/")
public class AmistadController {

	@Autowired
	private AmistadService service;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("solicitudesAceptadas")
	public List<Amistad> findBySolicitudesAceptadas(HttpServletRequest request) {

		Usuario usuario = getUsuarioActual(request);
		if (usuario != null) {

			return service.findByEstadoAndUsuario(AmistadEstado.ACEPTADA, usuario);

		}

		return new ArrayList<Amistad>();
	}

	@GetMapping("solicitudesDenegadas")
	public List<Amistad> findBySolicitudesDenegadas(HttpServletRequest request) {

		Usuario usuario = getUsuarioActual(request);
		if (usuario != null) {

			return service.findByEstadoAndUsuario(AmistadEstado.DENEGADA, usuario);

		}

		return new ArrayList<Amistad>();
	}

	@GetMapping("solicitudesPendientes")
	public List<Amistad> findBySolicitudesPendientes(HttpServletRequest request) {

		Usuario usuario = getUsuarioActual(request);
		if (usuario != null) {

			return service.findByEstadoAndUsuario(AmistadEstado.ENVIADA, usuario);

		}

		return new ArrayList<Amistad>();
	}
	
	@GetMapping("misSolicitudesPendientes")
	public List<Amistad> findByMisSolicitudesPendientes(HttpServletRequest request) {

		Usuario usuario = getUsuarioActual(request);
		if (usuario != null) {

			return service.findByEstadoAndUsuario(AmistadEstado.ENVIADA, usuario);

		}

		return new ArrayList<Amistad>();
	}

	public List<Amistad> findByUsuario(Usuario usuario) {
		return service.findByUsuario(usuario);
	}

	public List<Amistad> findByEstadoAndAmistad(AmistadEstado estado, Usuario amistado) {
		return service.findByEstadoAndAmistad(estado, amistado);
	}

	public List<Amistad> findByAmistad(Usuario amistad) {
		return service.findByAmistad(amistad);
	}

	@PostMapping("solicitud/{username}")
	public Amistad save(HttpServletRequest request,@PathVariable String username) {
		Usuario usuario = getUsuarioActual(request);
		if (usuario != null) {
			Usuario amigo = usuarioService.findByUsername(username);
			
			if (amigo != null) {
				
				Amistad amistad = new Amistad();
				
				amistad.setAmistad(amigo);
				amistad.setUsuario(usuario);
				amistad.setEstado(AmistadEstado.ENVIADA);

				return service.save(amistad);
			}
		}
		return null;
	}

	public void deleteById(Integer id) {
		service.deleteById(id);
	}

	private Usuario getUsuarioActual(final HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return usuarioService.findByUsername(principal.getName());
		}
		return null;
	}

}
