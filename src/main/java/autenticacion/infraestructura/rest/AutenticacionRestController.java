package autenticacion.infraestructura.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autenticacion.aplicacion.comando.CredencialesComando;
import autenticacion.aplicacion.comun.comando.RespuestaComando;
import autenticacion.aplicacion.manejador.AutenticacionManejador;

@RestController
@RequestMapping("/api/autenticacion")
public class AutenticacionRestController {

	@Autowired
	private AutenticacionManejador autenticacionManejador;
	
	@PostMapping
	@CrossOrigin
	public RespuestaComando<String> autenticacion(@Valid @RequestBody CredencialesComando credencialesComando) throws Exception {
		return autenticacionManejador.ejecutar(credencialesComando);	
	}
	
}
