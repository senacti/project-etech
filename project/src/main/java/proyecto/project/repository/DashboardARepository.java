package proyecto.project.repository;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DashboardARepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer findConteoIngresosHoy() {
        jakarta.persistence.Query query = entityManager.createNativeQuery("SELECT `Conteo de Ingresos Hoy` AS conteo FROM conteo_ingreso_hoy");
        Number conteo = (Number) query.getSingleResult();
        return conteo != null ? conteo.intValue() : null;
    }

    public Integer findConteoDiarioAgenda() {
        jakarta.persistence.Query query = entityManager.createNativeQuery("SELECT conteo FROM diario_agenda");
        Number conteo = (Number) query.getSingleResult();
        return conteo != null ? conteo.intValue() : 0;
    }

}
