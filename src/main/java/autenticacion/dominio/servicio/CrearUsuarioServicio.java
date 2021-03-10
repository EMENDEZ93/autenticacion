package autenticacion.dominio.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autenticacion.dominio.entidad.Usuario;
import autenticacion.dominio.repositorio.UsuarioRepositorio;

@Service
public class CrearUsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Usuario ejecutar(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}
	
}
