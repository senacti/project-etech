package proyecto.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.project.model.FormularioPaciente;
import proyecto.project.repository.FormularioPRepository;

@Service
public class FormularioPacienteService {

    @Autowired
    private FormularioPRepository formularioPRepository;

    public List<FormularioPaciente> findAll() {
        return formularioPRepository.findAll();
    }

    public FormularioPaciente save(FormularioPaciente paciente) {
        return formularioPRepository.save(paciente);
    }

    public List<FormularioPaciente> obtenerPacientes() {
        return formularioPRepository.findAll();
    }

    public FormularioPaciente findByIdentificacion(Long identificacionP) {
        return formularioPRepository.findByIdentificacionP(identificacionP);
    }

    public FormularioPaciente findById(Long id) {
        return formularioPRepository.findById(id).orElse(null);
    }

}
