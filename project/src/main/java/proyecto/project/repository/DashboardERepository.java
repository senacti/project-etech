package proyecto.project.repository;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class DashboardERepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> ejecutarAgendamientoEmpleado(int identificacionEmpleado) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("AgendamientoEmpleado")
            .registerStoredProcedureParameter(1, Integer.class, jakarta.persistence.ParameterMode.IN)
            .setParameter(1, identificacionEmpleado);
        
        storedProcedure.execute();
        return storedProcedure.getResultList();
    }
}
