package omarrm.ufps.web.apirest.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateController {

	@GetMapping("validate/")
	public boolean validate(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return true;
		}
		return false;
	}
	
}
