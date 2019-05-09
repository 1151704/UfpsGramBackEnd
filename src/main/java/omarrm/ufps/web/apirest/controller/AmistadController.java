package omarrm.ufps.web.apirest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omarrm.ufps.web.apirest.entity.Amistad;
import omarrm.ufps.web.apirest.entity.AmistadEstado;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.model.UsuarioApi;
import omarrm.ufps.web.apirest.service.AmistadService;
import omarrm.ufps.web.apirest.service.UsuarioService;

@RestController
@RequestMapping("amistad/")
public class AmistadController {

	@Autowired
	private AmistadService service;

	@Autowired
	private UsuarioService usuarioService;

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

	@PostMapping("aceptar/{username}")
	public Amistad aceptar(HttpServletRequest request,@PathVariable String username) {
		
		Usuario yo = getUsuarioActual(request);
		Usuario otro = usuarioService.findByUsername(username);
		
		if (yo != null && otro != null) {
			
			/* buscar la solicitud del otro donde este dirigida a mi*/
			Amistad solicitudOtro = service.findByUsuarioAndAmistad(otro, yo);
			if (solicitudOtro != null) {
				
				/*ACEPTAR SOLICITUD DEL OTRO*/
				solicitudOtro.setEstado(AmistadEstado.ACEPTADA);
				return service.save(solicitudOtro);
								
			}
			
		}
		return null;
	}
	
	@DeleteMapping("dejarDeSeguir/{username}")
	public void dejarDeSeguir(HttpServletRequest request,@PathVariable String username) {
		
		Usuario yo = getUsuarioActual(request);
		Usuario otro = usuarioService.findByUsername(username);
		
		if (yo != null && otro != null) {
			
			/* buscar la solicitud del otro donde este dirigida a mi*/
			Amistad solicitudMia = service.findByUsuarioAndAmistad(yo, otro);
			if (solicitudMia != null) {
				
				/*ACEPTAR SOLICITUD DEL OTRO*/
				service.deleteById(solicitudMia.getId());
								
			}
			
		}
	}
	
	@GetMapping("enviadas")
	public List<UsuarioApi> enviadas(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = usuarioService.findByUsername(principal.getName());
			return usuarioService.solicitudesEnviadas(user);
		}
		return new ArrayList<>();
	}

	@GetMapping("recibidas")
	public List<UsuarioApi> recibidas(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Usuario user = usuarioService.findByUsername(principal.getName());
			return usuarioService.solicitudesRecibidas(user);
		}
		return new ArrayList<>();
	}
	

	private Usuario getUsuarioActual(final HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return usuarioService.findByUsername(principal.getName());
		}
		return null;
	}

}
