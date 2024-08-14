package proyecto.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.project.repository.DashboardARepository;

@Service
public class DashboardAService {

    @Autowired
    private DashboardARepository dashboardARepository;

    public Integer obtenerConteoIngresosHoy() {
        return dashboardARepository.findConteoIngresosHoy();
    }

    public Integer obtenerConteoDiarioAgenda() {
        return dashboardARepository.findConteoDiarioAgenda();
    }

}
