package proyecto.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="acudiente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioAcudiente {
    @Id
    
    @Column (name = "ID_ACUDIENTE")
    private Long identificacionC;

    @Column (name = "IDENTIFICACION_P")
    private Long identificacionP;

    @Column (name = "ID_PARENTESCO")
    private Integer parentesco;
    
    @Column(name="NOMBRE_A")
    private String nombreA;

    @Column(name="APELLIDO_A")
    private String apellidoA;
    
    @Column(name="TELEFONO_A")
    private String telefonoA;

    @Column(name="CORREO_A")
    private String correoA;

    @Column(name="CIUDAD_A")
    private String ciudadA;

    @Column(name="DIRECCION_A")
    private String direccionA;

}
