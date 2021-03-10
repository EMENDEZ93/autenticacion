package autenticacion.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import autenticacion.aplicacion.consulta.UsuarioDto;
import autenticacion.dominio.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	public Usuario findByCorreo(String correo);	
	
	public Boolean existsByCorreo(String correo);

	@Transactional(readOnly = true)
	@Query("SELECT "
			+ "u.id as id, "
			+ "u.nombre as nombre, "
			+ "u.correo as correo "
			+ "FROM Usuario u")
	public List<UsuarioDto> listar();

	@Transactional(readOnly = true)
	@Query("SELECT "
			+ "u.id as id, "
			+ "u.nombre as nombre, "
			+ "u.correo as correo "
			+ "FROM Usuario u WHERE u.id = :idUsuario")
	public UsuarioDto obtenerUsuario(@Param("idUsuario") Long idUsuario);

}
