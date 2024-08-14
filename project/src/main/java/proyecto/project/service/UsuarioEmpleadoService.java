package proyecto.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.project.model.UsuarioEmpleado;
import proyecto.project.repository.LoginERepository;

@Service
public class UsuarioEmpleadoService {
    
    @Autowired
    private LoginERepository empleadoRepository; 

    public List<UsuarioEmpleado> obtenerEmpleados() {
        return empleadoRepository.findByIdRolE(2); 
    }
    
}
