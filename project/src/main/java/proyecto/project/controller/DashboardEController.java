package proyecto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import proyecto.project.service.DashboardEService;

@Controller
public class DashboardEController {

    @Autowired
    private DashboardEService dashboardEService;


    @GetMapping("/dashboardE")
    public String mostrarDashboardEmpleado(HttpSession session, Model model) {
        Long identificacionEmpleado = (Long) session.getAttribute("identificacionEmpleado"); // Recupera la identificación desde la sesión
        if (identificacionEmpleado != null) {
            Object[] ultimoAgendamiento = dashboardEService.obtenerUltimoAgendamientoEmpleado(identificacionEmpleado.intValue());
            model.addAttribute("ultimoAgendamiento", ultimoAgendamiento != null ? ultimoAgendamiento : new Object[]{});
        } else {
            return "redirect:/loginE";
        }

        
        return "dashboardE";
    }
    
    
}
