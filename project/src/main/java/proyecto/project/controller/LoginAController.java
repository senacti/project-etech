package proyecto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.project.model.UsuarioAdministrador;
import proyecto.project.service.LoginAService;

@Controller
public class LoginAController {

    @Autowired
    private LoginAService loginAService;

    @GetMapping("/loginA")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @PostMapping("/loginA")
    public String realizarLoginA(@RequestParam("identificacionA") Long identificacionA, @RequestParam("emailA") String emailA, @RequestParam("passwordA") String passwordA, Model model, HttpSession session) { // Agrega HttpSession como parámetro
        System.out.println("Intentando validar usuario: " + identificacionA + ", " + emailA + ", " + passwordA);
        
        UsuarioAdministrador usuario = loginAService.validarUsuario(identificacionA, emailA, passwordA);

        if (usuario != null && usuario.getIdRolA() == 1) {
            String nombreCompleto = usuario.getNombreA() + " " + usuario.getApellidoA();
            session.setAttribute("nombreAdmin", nombreCompleto); 
            return "redirect:/dashboardA";
        } else {
            // Usuario no encontrado o no es administrador
            model.addAttribute("loginError", "Error de identificación, email o contraseña.");
            return "login"; 
        }
    }    
}
