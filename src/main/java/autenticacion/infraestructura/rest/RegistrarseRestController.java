package autenticacion.infraestructura.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autenticacion.aplicacion.comando.UsuarioComando;
import autenticacion.aplicacion.comun.comando.RespuestaComando;
import autenticacion.aplicacion.manejador.ResgistroManejador;

@RestController
@RequestMapping("/registrarse")
public class RegistrarseRestController {

	@Autowired
	private ResgistroManejador resgistroManejador;
	
	@PostMapping
	public RespuestaComando<String> registrarse(@Valid @RequestBody UsuarioComando usuarioComando) throws Exception{
		return resgistroManejador.ejecutar(usuarioComando);
	}
	
}
