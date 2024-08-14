package proyecto.project.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "agenda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGENDA")
    private Long id;

    @Column(name = "ID_PROFESIONAL")
    private Integer idProfesional;

    @Column(name = "IDENTIFICACION_P")
    private Integer identificacionP;

    @Column(name = "ID_SERVICIO")
    private Integer idServicio;

    @Column(name = "ID_TURNO")
    private Integer idTurno;

    @Column(name = "INICIO_FH")
    private LocalDateTime inicioFh;

    @Column(name = "FIN_FH")
    private LocalDateTime finFh;

    @Column(name="DIARIO_FH")
    private LocalDateTime diarioFh;

}

    
