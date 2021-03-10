package autenticacion.aplicacion.comando;

import java.util.List;

import autenticacion.dominio.entidad.NombreRolConstante;

public class UsuarioComando {

	private String nombre;
	private String correo;
	private String contrasena;
	private List<NombreRolConstante> rol;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<NombreRolConstante> getRol() {
		return rol;
	}

	public void setRol(List<NombreRolConstante> rol) {
		this.rol = rol;
	}

}
