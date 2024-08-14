package proyecto.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proyecto.project.model.UsuarioAdministrador;

public interface LoginARepository extends JpaRepository<UsuarioAdministrador, Long> {
    @Query("SELECT u FROM UsuarioAdministrador u WHERE u.identificacionA = ?1 AND u.emailA = ?2 AND u.passwordA = ?3")
    UsuarioAdministrador findByIdentificacionAndEmailAndPassword(Long identificacionA, String emailA, String passwordA);

}
