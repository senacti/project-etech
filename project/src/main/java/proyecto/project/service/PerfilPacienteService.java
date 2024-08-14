package proyecto.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.project.repository.PerfilPacienteRepository;

@Service
public class PerfilPacienteService {

    @Autowired
    private PerfilPacienteRepository perfilPacienteRepository;

    public Object[] obtenerPerfilPaciente(int identificacionEmpleado) {
        List<Object[]> resultados = perfilPacienteRepository.ejecutarPerfilPaciente(identificacionEmpleado);
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        }
        return null; 
    }

}
