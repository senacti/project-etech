package proyecto.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proyecto.project.model.UsuarioEmpleado;
import java.util.List;

public interface LoginERepository extends JpaRepository<UsuarioEmpleado, Long> {
    @Query("SELECT u FROM UsuarioEmpleado u WHERE u.identificacionE = ?1 AND u.emailE = ?2 AND u.passwordE = ?3")
    UsuarioEmpleado findByIdentificacionAndEmailAndPassword(Long identificacionE, String emailE, String passwordE);

    // Agrega un nuevo método para buscar empleados por ID_ROL
    List<UsuarioEmpleado> findByIdRolE(Integer idRolE);
}
