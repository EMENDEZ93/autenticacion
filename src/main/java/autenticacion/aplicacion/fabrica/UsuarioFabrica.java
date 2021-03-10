package autenticacion.aplicacion.fabrica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import autenticacion.aplicacion.comando.UsuarioComando;
import autenticacion.dominio.entidad.Rol;
import autenticacion.dominio.entidad.Usuario;
import autenticacion.dominio.repositorio.RolRepositorio;

@Component
public class UsuarioFabrica {

	@Autowired(required = false)
	private PasswordEncoder encoder;	
	
	@Autowired
	private RolRepositorio rolRepositorio;

	public Usuario crear(UsuarioComando usuarioComando) {
		Usuario usuario = new Usuario(
				usuarioComando.getNombre(),
				usuarioComando.getCorreo(),
				encoder.encode(usuarioComando.getContrasena())
				);
		
		usuario.setRol(asignarRoles(usuarioComando));
		
		return usuario;
	}
	
	private List<Rol> asignarRoles(UsuarioComando usuarioComando) {
		List<Rol> rolesPorAsignar = new ArrayList<>();
		usuarioComando.getRol().forEach(rol -> {
			asignarRoles(rolesPorAsignar, rol.toString());
		});
		
		return rolesPorAsignar;
	}

	private void asignarRoles(List<Rol> rolesPorAsignar, String rol) {
		Rol r = rolRepositorio.findByNombre(rol);
		
		if(r != null) {
			rolesPorAsignar.add(r);			
		}
	}
	
}
