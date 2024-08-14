package proyecto.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.project.model.AgendaAdmin;
import proyecto.project.model.FormularioPaciente;
import proyecto.project.model.UsuarioEmpleado;
import proyecto.project.service.AgendaAdminService;
import proyecto.project.service.FormularioPacienteService;
import proyecto.project.service.UsuarioEmpleadoService;

@Controller
public class AgendaAdminController {

    @Autowired
    private UsuarioEmpleadoService empleadoService;

    @Autowired
    private FormularioPacienteService pacienteService;

    @Autowired
    private AgendaAdminService agendaService;

    @ModelAttribute
    public void addAttributes(Model model, HttpSession session) {
        String nombreAdmin = (String) session.getAttribute("nombreAdmin");

        if (nombreAdmin != null) {
            model.addAttribute("nombreAdmin", nombreAdmin);
        }

        List<UsuarioEmpleado> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados", empleados);

        List<FormularioPaciente> pacientes = pacienteService.obtenerPacientes();
        model.addAttribute("pacientes", pacientes);
    }

    @GetMapping("/AgendaAdmin")
    public String mostrarDashboardAdministrador() {
        return "AgendaAdmin";
    }

    @PostMapping("/AgendaAdmin")
    public String agendar(@RequestParam("empleado") Integer idEmpleado,
                        @RequestParam("paciente") Integer idPaciente,
                        @RequestParam("servicio") Integer idServicio,
                        @RequestParam("horario") Integer idTurno,
                        @RequestParam("inicio_fh") String inicioFh,
                        @RequestParam("fin_fh") String finFh, Model model) {
        AgendaAdmin nuevaAgenda = new AgendaAdmin();
        nuevaAgenda.setIdProfesional(idEmpleado);
        nuevaAgenda.setIdentificacionP(idPaciente);
        nuevaAgenda.setIdServicio(idServicio);
        nuevaAgenda.setIdTurno(idTurno);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        nuevaAgenda.setInicioFh(LocalDateTime.parse(inicioFh, formatter));
        nuevaAgenda.setFinFh(LocalDateTime.parse(finFh, formatter));

        agendaService.guardarAgenda(nuevaAgenda);

        model.addAttribute("agendaExitosa", true);

        return "AgendaAdmin";
    }
}
