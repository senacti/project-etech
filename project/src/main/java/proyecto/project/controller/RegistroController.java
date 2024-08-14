package proyecto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import proyecto.project.service.RegistroService;

@Controller
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    
    @GetMapping("/registro")
        public String mostrarDashboardAdministrador(HttpSession session, Model model) {
        String nombreAdmin = (String) session.getAttribute("nombreAdmin");
        if (nombreAdmin != null) {
            
        model.addAttribute("nombreAdmin", nombreAdmin);

        // Vista de ingresos
        model.addAttribute("registros", registroService.obtenerIngresosHoy());
        return "registro"; 
            
        }
        return "registro"; 
    }

}
