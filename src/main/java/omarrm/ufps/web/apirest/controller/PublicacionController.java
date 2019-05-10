package omarrm.ufps.web.apirest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omarrm.ufps.web.apirest.entity.Comentario;
import omarrm.ufps.web.apirest.entity.Etiqueta;
import omarrm.ufps.web.apirest.entity.Publicacion;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.model.ComentarioGuardar;
import omarrm.ufps.web.apirest.model.PublicacionGuardar;
import omarrm.ufps.web.apirest.service.PublicacionService;
import omarrm.ufps.web.apirest.service.UsuarioService;

@RestController
@RequestMapping("/publicacion/")
public class PublicacionController {

	@Autowired
	private PublicacionService service;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("")
	public Publicacion save(@RequestBody PublicacionGuardar data, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = usuarioService.findByUsername(principal.getName());
			if (data != null && user != null) {

				Publicacion p = new Publicacion();

				p.setUsuario(user);
				p.setDescripcion(data.getDescripcion());
				p.setUrlImagen(data.getImagen());

				if (data.getEtiquetas() != null) {
					Etiqueta e;
					p.setEtiquetas(new HashSet<Etiqueta>());
					for (String etiqueta : data.getEtiquetas()) {
						e = new Etiqueta();

						e.setMensaje(etiqueta);
						e.setPublicacion(p);

						p.getEtiquetas().add(e);

					}

				}

				return service.save(p);

			}
		}

		return null;
	}

	@GetMapping("")
	public List<Publicacion> misPublicaciones(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = usuarioService.findByUsername(principal.getName());
			if (user != null) {

				return service.findByUsuario(user);
			}

		}
		return new ArrayList<>();
	}

	@GetMapping("amigos")
	public List<Publicacion> publicacionesMisAmigos(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = usuarioService.findByUsername(principal.getName());
			if (user != null) {

				return service.findByAmigos(user);
			}

		}
		return new ArrayList<>();
	}

	@GetMapping("{username}")
	public List<Publicacion> publicaciones(@PathVariable String username) {
		if (username != null) {
			Usuario user = usuarioService.findByUsername(username);
			if (user != null) {

				return service.findByUsuario(user);
			}

		}
		return new ArrayList<>();
	}

	@PostMapping("comentar/{idPublicacion}")
	public Publicacion comentar(HttpServletRequest request, @RequestBody ComentarioGuardar comentario,
			@PathVariable Integer idPublicacion) {
		Principal principal = request.getUserPrincipal();
		if (principal != null && idPublicacion != null && idPublicacion > 0 && comentario != null) {
			Usuario user = usuarioService.findByUsername(principal.getName());
			if (user != null) {

				Publicacion p = service.findById(idPublicacion);

				if (p != null) {
					Comentario c = new Comentario();

					c.setUsuario(user);
					c.setComentario(comentario.getComentario());
					c.setUsuario(user);
					c.setPublicacion(p);

					p.getComentarios().add(c);

					service.save(p);

					return service.findById(idPublicacion);
				}

			}
		}

		return null;
	}

}
