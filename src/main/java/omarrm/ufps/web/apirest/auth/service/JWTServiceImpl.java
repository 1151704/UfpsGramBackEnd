package omarrm.ufps.web.apirest.auth.service;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import omarrm.ufps.web.apirest.auth.Constants;
import omarrm.ufps.web.apirest.entity.Usuario;
import omarrm.ufps.web.apirest.service.UsuarioService;

@Service
public class JWTServiceImpl implements JWTService {

	public static final long EXPIRATION_TIME = Constants.TOKEN_EXPIRATION_TIME;
	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static final String TOKEN_PREFIX = Constants.TOKEN_BEARER_PREFIX;
	public static final String HEADER_STRING = Constants.HEADER_AUTHORIZACION_KEY;
	
	@Autowired
	private UsuarioService service;
	
	@Override
	public String create(Authentication auth) throws IOException {

		String username = auth.getName();
		
		Usuario usuario = service.findByUsername(username);

		Claims claims = Jwts.claims();
		claims.put("username", username);
		claims.put("nombre", usuario.getNombre());
		claims.put("email", usuario.getEmail());

		Date inicio = new Date();

		Date fin = new Date(inicio.getTime() + EXPIRATION_TIME);

		String token = Jwts.builder().setClaims(claims).setSubject(username).signWith(SECRET_KEY).setIssuedAt(inicio)
				.setExpiration(fin).compact();

		return token;
	}

	@Override
	public boolean validate(String token) {
		return getClaims(token) != null;
	}

	@Override
	public Claims getClaims(String token) {

		Claims claims = null;
		try {

			claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(revolve(token)).getBody();

		} catch (Exception e) {
		}
		return claims;
	}

	@Override
	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {

			return claims.getSubject();

		}
		return null;
	}

	@Override
	public String revolve(String token) {
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			return token.replace(TOKEN_PREFIX, "");
		}
		return token;
	}

}
