package autenticacion.dominio.entidad;

import org.springframework.security.core.GrantedAuthority;

public enum NombreRolConstante implements GrantedAuthority {
	ROLE_USUARIO,
	ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}

}
