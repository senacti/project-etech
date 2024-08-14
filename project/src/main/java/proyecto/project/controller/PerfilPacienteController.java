package proyecto.project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.project.model.FormularioPaciente;
import proyecto.project.service.FormularioPacienteService;
import proyecto.project.service.PerfilPacienteService;

@Controller
public class PerfilPacienteController {

    @Autowired
    private PerfilPacienteService perfilPacienteService;
    
    @Autowired
    private FormularioPacienteService pacienteService;
    
    @GetMapping("/perfilPaciente")
    public String mostrarPerfilPaciente(HttpSession session, Model model){
        Long identificacionEmpleado = (Long) session.getAttribute("identificacionEmpleado"); // Recupera la identificación desde la sesión
        if (identificacionEmpleado != null) {
            Object[] perfilPaciente = perfilPacienteService.obtenerPerfilPaciente(identificacionEmpleado.intValue());
            if(perfilPaciente != null) {
                model.addAttribute("perfil", perfilPaciente);
            }
        }
        return "pe_paciente"; 
    }

    @GetMapping("/descargarPdf")
    public ResponseEntity<ByteArrayResource> descargarPdf(@RequestParam("identificacion") Long identificacion) throws IOException {
        FormularioPaciente paciente = pacienteService.findById(identificacion);

        if (paciente == null || paciente.getAntMedData() == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(paciente.getAntMedData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=historial.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .contentLength(paciente.getAntMedData().length)
                .body(resource);
    }

}