package proyecto.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class PerfilPacienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> ejecutarPerfilPaciente(int identificacionEmpleado) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("PerfilPaciente")
            .registerStoredProcedureParameter(1, Integer.class, jakarta.persistence.ParameterMode.IN)
            .setParameter(1, identificacionEmpleado);
        
        storedProcedure.execute();
        return storedProcedure.getResultList();
    }

}
