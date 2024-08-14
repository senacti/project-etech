package proyecto.project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import proyecto.project.model.FormularioPaciente;
import proyecto.project.service.FormularioPacienteService;

@Controller
public class FormularioPController {

    @Autowired
    private FormularioPacienteService pacienteService;

    @GetMapping("/pacientes")
    public String listPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.findAll());
        return "listaPacientes";
    }

    @GetMapping("/DatosPaciente")
    public String showForm(Model model) {
        model.addAttribute("paciente", new FormularioPaciente());
        return "formularioPaciente";
    }

    @PostMapping("/pacientes")
    public String savePaciente(@ModelAttribute FormularioPaciente paciente, HttpSession session) {
        session.setAttribute("paciente", paciente);
        return "redirect:/HistorialPaciente?idPaciente=" + paciente.getIdentificacionP();
    }

    @GetMapping("/HistorialPaciente")
    public String showForm2(@RequestParam("idPaciente") Long idPaciente, Model model, HttpSession session) {
        FormularioPaciente paciente = (FormularioPaciente) session.getAttribute("paciente");
        model.addAttribute("paciente", paciente);
        model.addAttribute("identificacionP", paciente.getIdentificacionP());
        return "formularioPaciente2";
    }

    @PostMapping("/pacientes2")
    public String savePaciente2(@ModelAttribute FormularioPaciente paciente,
            @RequestParam(value = "antMed", required = false) MultipartFile antMed,
            HttpSession session) {
        FormularioPaciente sessionPaciente = (FormularioPaciente) session.getAttribute("paciente");

        if (sessionPaciente == null) {
            // Manejar el error cuando no hay un "paciente" en la sesión
            return "redirect:/error?message=No se encontró el paciente en la sesión";
        }

        sessionPaciente.setIdEps(paciente.getIdEps());
        sessionPaciente.setRhP(paciente.getRhP());
        sessionPaciente.setPanal(paciente.getPanal());
        sessionPaciente.setMarcaPasos(paciente.getMarcaPasos());
        sessionPaciente.setAudifonos(paciente.getAudifonos());
        sessionPaciente.setProtesisDental(paciente.getProtesisDental());
        sessionPaciente.setAlergia(paciente.getAlergia());
        sessionPaciente.setNivelAutonomia(paciente.getNivelAutonomia());
        sessionPaciente.setNivelAlimentacion(paciente.getNivelAlimentacion());
        sessionPaciente.setNivelBano(paciente.getNivelBano());

        if (antMed != null && !antMed.isEmpty()) {
            if (!"application/pdf".equals(antMed.getContentType())) {
                return "redirect:/error?message=El archivo debe ser un PDF";
            }

            if (antMed.getSize() > 5 * 1024 * 1024) {
                return "redirect:/error?message=El archivo no debe ser mayor a 5 MB";
            }

            try {
                sessionPaciente.setAntMedData(antMed.getBytes());
            } catch (IOException e) {
                return "redirect:/error?message=Error al leer el archivo";
            }
        }
        pacienteService.save(sessionPaciente);
        return "redirect:/DatosAcudiente?identificacionP=" + sessionPaciente.getIdentificacionP();
    }

}
