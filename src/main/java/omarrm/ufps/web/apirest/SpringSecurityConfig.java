package omarrm.ufps.web.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfigurationSource;

import omarrm.ufps.web.apirest.auth.filter.JWTAuthenticationFilter;
import omarrm.ufps.web.apirest.auth.filter.JWTAuthorizationFilter;
import omarrm.ufps.web.apirest.auth.service.JWTService;
import omarrm.ufps.web.apirest.service.impl.JpaUserDetailsServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTService jwtService;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;	
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private CorsConfigurationSource corsConfigurationSource;
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors().configurationSource(corsConfigurationSource).and()
		.csrf().disable()
		.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/user/").permitAll()
        .antMatchers(HttpMethod.PUT, "/test/**").permitAll()
        .antMatchers("/validate/","/h2-console/**", "/favicon.ico").permitAll()
        .anyRequest().authenticated().and()
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
		.headers().frameOptions().sameOrigin();

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {

		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		 		
	}	
	

}
