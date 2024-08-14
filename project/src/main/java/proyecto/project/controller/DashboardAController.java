package proyecto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import proyecto.project.service.DashboardAService;

@Controller
public class DashboardAController {

    @Autowired
    private DashboardAService dashboardAService;

    @GetMapping("/dashboardA")
    public String mostrarDashboardAdministrador(HttpSession session, Model model) {
        String nombreAdmin = (String) session.getAttribute("nombreAdmin");

        model.addAttribute("nombreAdmin", nombreAdmin);
        
        Integer conteoIngresosHoy = dashboardAService.obtenerConteoIngresosHoy();
        model.addAttribute("conteoIngresosHoy", conteoIngresosHoy);

        Integer conteoDiarioAgenda = dashboardAService.obtenerConteoDiarioAgenda();
        model.addAttribute("conteoDiarioAgenda", conteoDiarioAgenda);

        return "dashboardA"; 
    }
}
