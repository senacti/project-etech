package proyecto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyecto.project.model.FormularioAcudiente;
import proyecto.project.service.FormularioAcudienteService;

@Controller
public class FormularioCController {

    @Autowired
    private FormularioAcudienteService acudienteService;

    @GetMapping("/acudientes")
    public String listAcudientes(Model model) {
        model.addAttribute("acudientes", acudienteService.findAll());
        return "listaAcudientes";
    }

    @GetMapping("/DatosAcudiente")
    public String showForm(@RequestParam(required = false) Long identificacionP, Model model) {
        if (identificacionP != null) {
            model.addAttribute("identificacionP", identificacionP);
        }
        model.addAttribute("acudiente", new FormularioAcudiente());
        return "formularioAcudiente";
    }
    
    @PostMapping("/acudientes")
    public String saveAcudiente(@ModelAttribute FormularioAcudiente acudiente,
                                @RequestParam("identificacionP") Long identificacionP,
                                Model model) {
        acudienteService.save(acudiente);
        return "redirect:/home";
    }
    
}
