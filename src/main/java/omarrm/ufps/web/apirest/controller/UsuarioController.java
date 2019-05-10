package omarrm.ufps.web.apirest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.model.UsuarioApi;
import omarrm.ufps.web.apirest.service.UsuarioService;

@RestController
@RequestMapping("user/")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("")
	public Usuario get(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(principal.getName());

			return user;
		}
		return null;
	}
	
	@GetMapping("{username}")
	public Usuario get(HttpServletRequest request, @PathVariable String username) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(username);

			return user;
		}
		return null;
	}

	@PostMapping("")
	public Usuario save(@RequestBody Usuario usuario) {

		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		return service.save(usuario);
	}

	@PutMapping("")
	public Usuario update(HttpServletRequest request, @RequestBody Usuario usuario) {
		Principal principal = request.getUserPrincipal();
		if (principal != null && principal.getName().equals(usuario.getUsername())) {
			Usuario user = service.findById(usuario.getId());

			user.setEmail(usuario.getEmail());
			user.setNombre(usuario.getNombre());
			user.setFechaNacimiento(usuario.getFechaNacimiento());

			return service.save(user);
		}
		return null;
	}

	@GetMapping("users/{filter}")
	public List<UsuarioApi> filterUser(HttpServletRequest request, @PathVariable String filter) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(principal.getName());
			return service.busqueda("%" + filter + "%", user);
		}
		return new ArrayList<>();
	}

	@GetMapping("seguidores")
	public List<UsuarioApi> seguidores(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(principal.getName());
			return service.seguidores(user);
		}
		return new ArrayList<>();
	}

	@GetMapping("siguiendo")
	public List<UsuarioApi> siguiendo(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(principal.getName());
			return service.siguiendo(user);
		}
		return new ArrayList<>();
	}

	@GetMapping("seguidores/{username}")
	public long seguidoresTotal(HttpServletRequest request, @PathVariable String username) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(principal.getName());
			return service.siguiendoTotal(user);
		}
		return 0;
	}

	@GetMapping("siguiendo/{username}")
	public long siguiendoTotal(HttpServletRequest request, @PathVariable String username) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = service.findByUsername(username);
			return service.siguiendoTotal(user);
		}
		return 0;
	}

}
