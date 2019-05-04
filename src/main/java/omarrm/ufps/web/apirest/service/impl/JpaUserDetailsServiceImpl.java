package omarrm.ufps.web.apirest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.service.UsuarioService;


@Service
public class JpaUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService service;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = service.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Username: '" + username + "' no existe!");
		}
		
		return new User(username, usuario.getPassword(), true, true, true, true, new ArrayList<>());
	}

}
