package proyecto.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmpleado {
    @Id

    @Column(name = "IDENTIFICACION")
    private Long identificacionE;
    @Column(name = "ID_ROL")
    private Integer idRolE;
    @Column(name = "CORREO_ELEC")
    private String emailE;
    @Column(name = "CONTRASEÑA")
    private String passwordE;
    @Column(name="NOMBRE")
    private String nombreE;
    @Column(name="APELLIDO")
    private String apellidoE;
}
