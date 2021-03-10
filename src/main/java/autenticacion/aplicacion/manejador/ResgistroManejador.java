package autenticacion.aplicacion.manejador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autenticacion.aplicacion.comando.UsuarioComando;
import autenticacion.aplicacion.comun.comando.RespuestaComando;
import autenticacion.aplicacion.comun.manejador.RespuestaManejador;
import autenticacion.aplicacion.fabrica.UsuarioFabrica;
import autenticacion.dominio.entidad.Usuario;
import autenticacion.dominio.servicio.CrearUsuarioServicio;

@Component
public class ResgistroManejador implements RespuestaManejador<UsuarioComando, RespuestaComando<String>> {

	@Autowired
	private UsuarioFabrica usuarioFabrica;

	@Autowired
	private CrearUsuarioServicio crearUsuarioServicio;

	@Override
	public RespuestaComando<String> ejecutar(UsuarioComando usuarioComando) throws Exception {
		Usuario usuario = usuarioFabrica.crear(usuarioComando);
		return new RespuestaComando<>(crearUsuarioServicio.ejecutar(usuario).getNombre());
	}

}
