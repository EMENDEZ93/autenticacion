package autenticacion.infraestructura.configuracion.seguridad.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import autenticacion.dominio.entidad.Usuario;
import autenticacion.dominio.repositorio.UsuarioRepositorio;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio userRepo;

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

		final Usuario user = userRepo.findByCorreo(correo);

		usuarioNoDebeSerNull(correo, user);

		return org.springframework.security.core.userdetails.User//
				.withUsername(correo)
				.password(user.getContrasena())
				.authorities(user.getRol())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}

	private void usuarioNoDebeSerNull(String correo, final Usuario user) {
		if (user == null) {
			throw new UsernameNotFoundException("Usuario con el siguiente correo '" + correo + "' no existe");
		}
	}

}
