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
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAdministrador {
    @Id

    @Column(name = "IDENTIFICACION")
    private Long identificacionA;
    @Column(name = "ID_ROL")
    private Integer idRolA;
    @Column(name = "CORREO_ELEC")
    private String emailA;
    @Column(name = "CONTRASEÑA")
    private String passwordA;
    @Column(name="NOMBRE")
    private String nombreA;
    @Column(name="APELLIDO")
    private String apellidoA;

}
