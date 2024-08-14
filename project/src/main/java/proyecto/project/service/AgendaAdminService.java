package proyecto.project.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import proyecto.project.model.AgendaAdmin;
import proyecto.project.repository.AgendaAdminRepository;

@Service

public class AgendaAdminService {

    @Autowired
    private AgendaAdminRepository agendaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AgendaAdmin guardarAgenda(AgendaAdmin agenda) {
        return agendaRepository.save(agenda);
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> obtenerReporteAgenda() {
        Query query = entityManager.createNativeQuery("CALL ReporteAgenda()");
        return query.getResultList();
    }

    public void eliminarReportePorId(Long id) {
        agendaRepository.deleteById(id);
    }

    @SuppressWarnings("unchecked")
    public Object[] obtenerDatosParaEdicion(Long id) {
    Query query = entityManager.createNativeQuery("CALL ReporteAgenda()");
    List<Object[]> resultados = query.getResultList();

    for (Object[] resultado : resultados) {
        if (((Long) resultado[0]).equals(id)) {
            return resultado;
        }
    }

    return null;
}

    public void editarReporte(Long idAgenda, LocalDateTime diario, LocalDateTime inicioFH, LocalDateTime finFH) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("EditarAgenda")
            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN)
            .registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN)
            .registerStoredProcedureParameter(4, Timestamp.class, ParameterMode.IN)
            .setParameter(1, idAgenda)
            .setParameter(2, Timestamp.valueOf(diario))
            .setParameter(3, Timestamp.valueOf(inicioFH))
            .setParameter(4, Timestamp.valueOf(finFH));
    
        query.execute();
    }

}

