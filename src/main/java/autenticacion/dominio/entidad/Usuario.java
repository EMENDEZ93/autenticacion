package autenticacion.dominio.entidad;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "correo" }) })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 50)
	private String nombre;

	@NaturalId
	@NotBlank
	@Size(max = 50)
	@Email
	private String correo;

	@NotBlank
	@Size(min = 6, max = 100)
	private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> rol;	    
    
	public Usuario() {
	}

	public Usuario(String nombre, String correo, String contrasena) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
	}
	
	public Usuario(Long id,String nombre) {
		this.id=id;
		this.nombre = nombre;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public void setUserid(String userid) {
		this.nombre = userid;
	}

	public List<SimpleGrantedAuthority> getRol() {
		return rol
				.stream()
				.map(s -> new SimpleGrantedAuthority(s.getNombre())).filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

}
