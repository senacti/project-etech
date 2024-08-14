package proyecto.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.project.model.FormularioPaciente;


public interface FormularioPRepository extends JpaRepository<FormularioPaciente, Long>{
    FormularioPaciente findByIdentificacionP(Long identificacionP);
} 
