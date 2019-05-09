package omarrm.ufps.web.apirest.auth.service;

import java.io.IOException;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;

public interface JWTService {

	public String create(Authentication auth) throws IOException;

	public boolean validate(String token);

	public Claims getClaims(String token);

	public String getUserName(String token);
	
	public String revolve(String token);

}
