package omarrm.ufps.web.apirest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import omarrm.ufps.web.apirest.auth.CustomAccessDeniedHandler;
import omarrm.ufps.web.apirest.auth.CustomAuthenticationEntryPoint;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

}
