package proyecto.project;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import proyecto.project.model.AgendaAdmin;
import proyecto.project.model.UsuarioAdministrador;
import proyecto.project.service.AgendaAdminService;
import proyecto.project.service.LoginAService;

@SpringBootTest
@AutoConfigureMockMvc
class AgendaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendaAdminService agendaService;

    @MockBean
    private LoginAService loginAService;

    @Test
    void testLoginAndAgendar() throws Exception {
        // Configurar los datos de entrada para el inicio de sesión
        Long identificacionA = 52072773L;
        String emailA = "eladio@gmail.com";
        String passwordA = "Eladio56";
        
        // Configurar el mock del usuario administrador
        UsuarioAdministrador admin = new UsuarioAdministrador();
        admin.setIdRolA(1);  // Asumimos que el rol 1 es de administrador
        admin.setNombreA("Admin");
        admin.setApellidoA("Test");
        
        // Simular que el servicio devuelve el usuario administrador
        when(loginAService.validarUsuario(identificacionA, emailA, passwordA)).thenReturn(admin);

        // Crear una sesión simulada
        MockHttpSession session = new MockHttpSession();

        // Realizar la solicitud de inicio de sesión
        mockMvc.perform(post("/loginA")
                .param("identificacionA", identificacionA.toString())
                .param("emailA", emailA)
                .param("passwordA", passwordA)
                .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboardA"));

        // Datos de entrada para la prueba de agendar
        String idEmpleado = "10011223";
        String idPaciente = "6345234";
        String idServicio = "200";
        String idTurno = "500";
        String inicioFh = "2024-08-13T10:00";
        String finFh = "2024-08-13T11:00";

        // Realizar la solicitud POST para agendar usando la sesión autenticada
        mockMvc.perform(post("/AgendaAdmin")
                .param("empleado", idEmpleado)
                .param("paciente", idPaciente)
                .param("servicio", idServicio)
                .param("horario", idTurno)
                .param("inicio_fh", inicioFh)
                .param("fin_fh", finFh)
                .session(session))  // Usar la sesión autenticada
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("agendaExitosa"));

        // Capturar y verificar los argumentos pasados a guardarAgenda
        ArgumentCaptor<AgendaAdmin> agendaCaptor = ArgumentCaptor.forClass(AgendaAdmin.class);
        verify(agendaService, times(1)).guardarAgenda(agendaCaptor.capture());

        AgendaAdmin agendaEnviada = agendaCaptor.getValue();

        // Verificar que los valores dentro de AgendaAdmin son los esperados
        assertEquals(10011223, agendaEnviada.getIdProfesional());
        assertEquals(6345234, agendaEnviada.getIdentificacionP());
        assertEquals(200, agendaEnviada.getIdServicio());
        assertEquals(500, agendaEnviada.getIdTurno());
        assertEquals(LocalDateTime.parse(inicioFh), agendaEnviada.getInicioFh());
        assertEquals(LocalDateTime.parse(finFh), agendaEnviada.getFinFh());
    }
}
