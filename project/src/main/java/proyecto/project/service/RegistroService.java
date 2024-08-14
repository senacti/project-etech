package proyecto.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.project.repository.RegistroRepository;
import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Object[]> obtenerIngresosHoy() {
        return registroRepository.findIngresoHoy();
    }
}
