package autenticacion.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import autenticacion.aplicacion.consulta.RolDto;
import autenticacion.dominio.entidad.NombreRolConstante;
import autenticacion.dominio.entidad.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long>{

	public Rol findByNombre(String nombre);

	public abstract boolean existsByNombre(NombreRolConstante nombre);

	@Transactional(readOnly = true)
	@Query("SELECT r.id as id, r.nombre as nombre FROM Rol r")
	public List<RolDto> listar();	
	
}
