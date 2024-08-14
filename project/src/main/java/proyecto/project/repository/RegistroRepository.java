package proyecto.project.repository;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RegistroRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> findIngresoHoy() {
        jakarta.persistence.Query query = entityManager.createNativeQuery("SELECT CONCAT(p.NOMBRE_P, ' ', p.APELLIDO_P) AS NombreDelPaciente, CONCAT(a.NOMBRE_A, ' ', a.APELLIDO_A) AS NombreDelAcudiente, p.DIRECCION_P AS Ubicacion FROM dts_paciente p JOIN acudiente a ON (p.IDENTIFICACION_P = a.IDENTIFICACION_P) WHERE p.fecha_ingreso >= CURDATE()");
        return query.getResultList();
    }
}
