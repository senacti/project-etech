package proyecto.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.project.service.AgendaAdminService;

@Controller
public class ReporteAgendaController {

    @Autowired
    private AgendaAdminService agendaService;

    @GetMapping("/reporteAgenda")
    public String mostrarReporteAgenda(HttpSession session, Model model) {
        String nombreAdmin = (String) session.getAttribute("nombreAdmin");
        if (nombreAdmin != null) {
            model.addAttribute("nombreAdmin", nombreAdmin);
            List<Object[]> reporteAgenda = agendaService.obtenerReporteAgenda();
            model.addAttribute("reporteAgenda", reporteAgenda);
        }
        return "reporteAgenda";
    }

    @GetMapping("/editarReporte")
    public String mostrarFormularioDeEdicion(@RequestParam("id") Long id, Model model) {
        Object[] datosReporte = agendaService.obtenerDatosParaEdicion(id);
        if (datosReporte != null) {
            model.addAttribute("idAgenda", datosReporte[0]);
            model.addAttribute("diario", datosReporte[1]);
            model.addAttribute("nombrePaciente", datosReporte[2]);
            model.addAttribute("nombreEmpleado", datosReporte[3]);
            model.addAttribute("TpoServicio",datosReporte[4]);
            model.addAttribute("TpoTurno",datosReporte[5]);
            model.addAttribute("inicioFH", datosReporte[6].toString().replace(" ", "T"));
            model.addAttribute("finFH", datosReporte[7].toString().replace(" ", "T"));
        }
        
        return "reporteAgendaEditar";
    }

@PostMapping("/actualizarReporte")
public String actualizarReporte(@RequestParam("idAgenda") Long idAgenda, 
                                @RequestParam("diario") String diario,
                                @RequestParam("inicioFH") String inicioFH, 
                                @RequestParam("finFH") String finFH) {
    // Convertir los Strings a LocalDateTime
    LocalDateTime diarioDateTime = LocalDateTime.parse(diario, DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime inicioFHDateTime = LocalDateTime.parse(inicioFH, DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime finFHDateTime = LocalDateTime.parse(finFH, DateTimeFormatter.ISO_DATE_TIME);

    // Llamar al método editarReporte del servicio
    agendaService.editarReporte(idAgenda, diarioDateTime, inicioFHDateTime, finFHDateTime);

    return "redirect:/reporteAgenda";
}

    @PostMapping("/eliminarReporte")
    public String eliminarReporte(@RequestParam("id") Long id) {
        agendaService.eliminarReportePorId(id);
        return "redirect:/reporteAgenda";
    }

}