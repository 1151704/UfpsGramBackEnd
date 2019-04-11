package omarrm.ufps.web.apirest.auth;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<>();

		body.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		body.put("status", 403);
		body.put("message", "Acceso denegado");
		
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		
	}

}
