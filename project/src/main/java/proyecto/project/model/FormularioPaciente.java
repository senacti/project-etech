package proyecto.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table (name = "dts_paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioPaciente {
    @Id

    @Column (name = "IDENTIFICACION_P")
    private Long identificacionP;

    @Column (name = "T_ID")
    private String tId;

    @Column(name = "NOMBRE_P")
    private String nombreP;

    @Column(name = "APELLIDO_P")
    private String apellidoP;

    @Column(name = "DIRECCION_P")
    private String direccionP;

    @Column(name = "RH_P")
    private String rhP;

    @Column(name = "ID_EPS")
    private Integer idEps;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "F_NAC")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fNac;    

    @Column(name = "ALERGIA")
    private String alergia;

    @Column(name = "NIVEL_ALIMENTACION")
    private String nivelAlimentacion;

    @Column(name = "NIVEL_BAÑO")
    private String nivelBano;

    @Column(name = "NIVEL_AUTONOMIA")
    private String nivelAutonomia;
    
    @Transient
    private MultipartFile antMed;

    @Lob
    @Column(name = "ANT_MED", columnDefinition = "LONGBLOB")
    private byte[] antMedData;

    @Column(name = "PAÑAL")
    private String panal;

    @Column(name = "AUDIFONOS")
    private String audifonos;

    @Column(name = "MARCA_PASOS")
    private String marcaPasos;

    @Column(name = "PROTESIS_DENTAL")
    private String protesisDental;
}
