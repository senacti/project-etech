package proyecto.project.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class NotaEnfermeriaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void guardarNotaEnfermeria(
        Integer fcBpm, String paMmhg, Integer tempC, Integer spo2Pct, Integer frRpm, Integer gluMgdl,
        Integer identificacionP, Integer idProfesional, String actividad, String medicamento, 
        String notaEnfer, byte[] evidFotografica) {
        
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("NotaEnfermeria")
            .registerStoredProcedureParameter(1, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(2, String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(3, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(4, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(5, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(6, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(7, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(8, Integer.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(9, String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(10, String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(11, String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(12, byte[].class, jakarta.persistence.ParameterMode.IN)
            .setParameter(1, fcBpm)
            .setParameter(2, paMmhg)
            .setParameter(3, tempC)
            .setParameter(4, spo2Pct)
            .setParameter(5, frRpm)
            .setParameter(6, gluMgdl)
            .setParameter(7, identificacionP)
            .setParameter(8, idProfesional)
            .setParameter(9, actividad)
            .setParameter(10, medicamento)
            .setParameter(11, notaEnfer)
            .setParameter(12, evidFotografica);
            
        query.execute();
    }
}
