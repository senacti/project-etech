package proyecto.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.project.model.UsuarioAdministrador;
import proyecto.project.repository.LoginARepository;


@Service
public class LoginAService {

    @Autowired
    private LoginARepository loginARepository;

    public UsuarioAdministrador validarUsuario(Long identificacionA, String emailA, String passwordA) {
        return loginARepository.findByIdentificacionAndEmailAndPassword(identificacionA, emailA, passwordA);
    }

}