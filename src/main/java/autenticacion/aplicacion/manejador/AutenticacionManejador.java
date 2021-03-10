package autenticacion.aplicacion.manejador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import autenticacion.aplicacion.comando.CredencialesComando;
import autenticacion.aplicacion.comun.comando.RespuestaComando;
import autenticacion.aplicacion.comun.manejador.RespuestaManejador;
import autenticacion.dominio.repositorio.UsuarioRepositorio;
import autenticacion.infraestructura.configuracion.seguridad.exception.SecurityJwtException;
import autenticacion.infraestructura.configuracion.seguridad.jwt.JwtTokenProvider;

@Component
public class AutenticacionManejador implements RespuestaManejador<CredencialesComando, RespuestaComando<String>> {

	@Autowired(required = false)
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public RespuestaComando<String> ejecutar(CredencialesComando credencialesComando) throws Exception {
	    try {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credencialesComando.getCorreo(), credencialesComando.getContrasena()));	    
	    
	        return new RespuestaComando<>(jwtTokenProvider.createToken(credencialesComando.getCorreo(),  usuarioRepositorio.findByCorreo(credencialesComando.getCorreo()).getRol() ));
	    } catch (AuthenticationException e) {
	        throw new SecurityJwtException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	}

}
