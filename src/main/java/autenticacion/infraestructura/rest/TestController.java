package autenticacion.infraestructura.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TestController {

	@GetMapping
	public String get() {
		return "hasRole('ROLE_ADMIN')";
	}
	
}
