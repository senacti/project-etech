package proyecto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession; 

import proyecto.project.model.UsuarioEmpleado;
import proyecto.project.service.LoginEService;

@Controller
public class LoginEController {

    @Autowired
    private LoginEService loginEService;

@PostMapping("/loginE")
public String realizarLoginE(@RequestParam("identificacionE") Long identificacionE, @RequestParam("emailE") String emailE, @RequestParam("passwordE") String passwordE, Model model, HttpSession session) { // Añade HttpSession como parámetro
    UsuarioEmpleado usuario = loginEService.validarUsuario(identificacionE, emailE, passwordE);
    if (usuario != null && usuario.getIdRolE() == 2) {
        session.setAttribute("identificacionEmpleado", usuario.getIdentificacionE()); 
        return "redirect:/dashboardE"; 
    }

    model.addAttribute("loginError", "Error de identificación, email o contraseña.");
    return "login";
}
}
