package omarrm.ufps.web.apirest.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omarrm.ufps.web.apirest.entity.Usuario;
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

}
