package proyecto.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.project.repository.DashboardERepository;
import java.util.List;

@Service
public class DashboardEService {

    @Autowired
    private DashboardERepository dashboardERepository;

    public Object[] obtenerUltimoAgendamientoEmpleado(int identificacionEmpleado) {
        List<Object[]> resultados = dashboardERepository.ejecutarAgendamientoEmpleado(identificacionEmpleado);
        if (!resultados.isEmpty()) {
            return resultados.get(resultados.size() - 1);
        }
        return null; 
    }
    
}
