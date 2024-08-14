package proyecto.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.project.model.UsuarioEmpleado;
import proyecto.project.repository.LoginERepository;


@Service
public class LoginEService {

    @Autowired
    private LoginERepository loginERepository;

    public UsuarioEmpleado validarUsuario(Long identificacionE, String emailE, String passwordE) {
        return loginERepository.findByIdentificacionAndEmailAndPassword(identificacionE, emailE, passwordE);
    }

}