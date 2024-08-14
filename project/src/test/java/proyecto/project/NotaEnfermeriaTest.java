package proyecto.project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import proyecto.project.model.UsuarioEmpleado;
import proyecto.project.service.LoginEService;
import proyecto.project.service.NotaEnfermeriaService;

@SpringBootTest
@AutoConfigureMockMvc
class NotaEnfermeriaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaEnfermeriaService notaEnfermeriaService;

    @MockBean
    private LoginEService loginEService;

    @Test
    void testLoginAndEnfermeria() throws Exception {
        // Configurar los datos de entrada para el inicio de sesión
        Long identificacionE = 11122334L;
        String emailE = "valeria@gmail.com";
        String passwordE = "Valeria11";
        
        // Configurar el mock del usuario empleado
        UsuarioEmpleado emp = new UsuarioEmpleado();
        emp.setIdRolE(2);
        emp.setNombreE("Valeria");
        emp.setApellidoE("Test");

        // Simular que el servicio devuelve el usuario empleado
        when(loginEService.validarUsuario(identificacionE, emailE, passwordE)).thenReturn(emp);

        // Crear una sesión simulada
        MockHttpSession session = new MockHttpSession();

        // Realizar la solicitud de inicio de sesión
        mockMvc.perform(((MockHttpServletRequestBuilder) post("/loginE"))
                .param("identificacionE", identificacionE.toString())
                .param("emailE", emailE)
                .param("passwordE", passwordE)
                .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboardE"));

        // Datos de entrada para la prueba de nota de enfermería
        Integer fcBpm = 50;
        String paMmhg = "70";
        Integer tempC = 35;
        Integer spo2Pct = 58;
        Integer frRpm = 35;
        Integer gluMgdl = 59;
        Integer identificacionP = 796986;
        Integer idProfesional = 11122334;
        String actividad = "Estiramientos con mancuernas y caminata";
        String medicamento = "Acetaminofen";
        String notaEnfer = "El paciente se encuentra con un bajo nivel de oxígeno en sangre pero se desempeña bien";
        
        // Simular un archivo MultipartFile
        MockMultipartFile mockFile = new MockMultipartFile("evidFotografica", "evidencia.jpg", "image/jpeg", "test content".getBytes());

        // Realizar la solicitud POST para guardar la nota de enfermería usando la sesión autenticada
        mockMvc.perform(multipart("/guardarNota")
                .file(mockFile)
                .param("fcBpm", fcBpm.toString())
                .param("paMmhg", paMmhg)
                .param("tempC", tempC.toString())
                .param("spo2Pct", spo2Pct.toString())
                .param("frRpm", frRpm.toString())
                .param("gluMgdl", gluMgdl.toString())
                .param("identificacionP", identificacionP.toString())
                .param("idProfesional", idProfesional.toString())
                .param("actividad", actividad)
                .param("medicamento", medicamento)
                .param("notaEnfer", notaEnfer)
                .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notaEnfermeria"));

        // Capturar y verificar los argumentos pasados a guardarNotaEnfermeria
        ArgumentCaptor<Integer> fcBpmCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> paMmhgCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> tempCCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> spo2PctCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> frRpmCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> gluMgdlCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> identificacionPCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> idProfesionalCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> actividadCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> medicamentoCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> notaEnferCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<byte[]> evidFotograficaCaptor = ArgumentCaptor.forClass(byte[].class);

        verify(notaEnfermeriaService, times(1)).guardarNotaEnfermeria(
                fcBpmCaptor.capture(),
                paMmhgCaptor.capture(),
                tempCCaptor.capture(),
                spo2PctCaptor.capture(),
                frRpmCaptor.capture(),
                gluMgdlCaptor.capture(),
                identificacionPCaptor.capture(),
                idProfesionalCaptor.capture(),
                actividadCaptor.capture(),
                medicamentoCaptor.capture(),
                notaEnferCaptor.capture(),
                evidFotograficaCaptor.capture()
        );

        // Verificar que los valores son los esperados
        assertEquals(fcBpm, fcBpmCaptor.getValue());
        assertEquals(paMmhg, paMmhgCaptor.getValue());
        assertEquals(tempC, tempCCaptor.getValue());
        assertEquals(spo2Pct, spo2PctCaptor.getValue());
        assertEquals(frRpm, frRpmCaptor.getValue());
        assertEquals(gluMgdl, gluMgdlCaptor.getValue());
        assertEquals(identificacionP, identificacionPCaptor.getValue());
        assertEquals(idProfesional, idProfesionalCaptor.getValue());
        assertEquals(actividad, actividadCaptor.getValue());
        assertEquals(medicamento, medicamentoCaptor.getValue());
        assertEquals(notaEnfer, notaEnferCaptor.getValue());
        assertArrayEquals(mockFile.getBytes(), evidFotograficaCaptor.getValue());
    }

}
