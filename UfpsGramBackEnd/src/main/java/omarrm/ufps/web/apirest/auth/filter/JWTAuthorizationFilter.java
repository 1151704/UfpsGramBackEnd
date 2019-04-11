package omarrm.ufps.web.apirest.auth.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import omarrm.ufps.web.apirest.auth.service.JWTService;
import omarrm.ufps.web.apirest.auth.service.JWTServiceImpl;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(JWTServiceImpl.HEADER_STRING);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		boolean validToken = jwtService.validate(header);

		UsernamePasswordAuthenticationToken authentication = null;

		if (validToken) {

			String username = jwtService.getUserName(header);
					
			authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	protected boolean requiresAuthentication(String header) {

		return !(header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX));

	}

}
