package proyecto.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import proyecto.project.service.NotaEnfermeriaService;
import proyecto.project.service.PerfilPacienteService;

@Controller
public class NotaEnfermeriaController {

    @Autowired
    private NotaEnfermeriaService notaEnfermeriaService;

    private final PerfilPacienteService perfilPacienteService;

    public NotaEnfermeriaController(PerfilPacienteService perfilPacienteService) {
        this.perfilPacienteService = perfilPacienteService;
    }

    @GetMapping("/notaEnfermeria")
    public String mostrarNotaEnfermeria(HttpSession session, Model model) {
        Long identificacionEmpleado = (Long) session.getAttribute("identificacionEmpleado");
        if (identificacionEmpleado != null) {
            Object[] perfilPaciente = perfilPacienteService.obtenerPerfilPaciente(identificacionEmpleado.intValue());
            if (perfilPaciente != null && perfilPaciente.length > 0) {
                model.addAttribute("perfil", perfilPaciente);
                model.addAttribute("idEmpleado", perfilPaciente[2]); 
                model.addAttribute("idPaciente", perfilPaciente[5]);
            }
        }
        return "ne_paciente";
    }

    @PostMapping("/guardarNota")
    public String guardarNotaEnfermeria(
            @RequestParam("fcBpm") Integer fcBpm,
            @RequestParam("paMmhg") String paMmhg,
            @RequestParam("tempC") Integer tempC,
            @RequestParam("spo2Pct") Integer spo2Pct,
            @RequestParam("frRpm") Integer frRpm,
            @RequestParam("gluMgdl") Integer gluMgdl,
            @RequestParam("identificacionP") Integer identificacionP,
            @RequestParam("idProfesional") Integer idProfesional,
            @RequestParam("actividad") String actividad,
            @RequestParam("medicamento") List<String> medicamentos,
            @RequestParam("notaEnfer") String notaEnfer,
            @RequestParam("evidFotografica") MultipartFile evidFotografica,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws IOException {

        String medicamentoFormato = String.join(", ", medicamentos); 

        // Convertir el archivo en un arreglo de bytes
        byte[] evidFotograficaBytes = evidFotografica.isEmpty() ? null : evidFotografica.getBytes();
        

        notaEnfermeriaService.guardarNotaEnfermeria(
                fcBpm, paMmhg, tempC, spo2Pct, frRpm, gluMgdl,
                identificacionP, idProfesional, actividad, medicamentoFormato, 
                notaEnfer, evidFotograficaBytes
        );

        redirectAttributes.addFlashAttribute("notaEnviada", true);
        redirectAttributes.addFlashAttribute("mensaje", "La nota ha sido enviada correctamente.");

        return "redirect:/notaEnfermeria";

    }
}

