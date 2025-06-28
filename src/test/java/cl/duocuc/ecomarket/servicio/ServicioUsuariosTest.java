package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.modelo.PersistenciaSP;
import cl.duocuc.ecomarket.modelo.dto.usuario.ClienteRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.usuario.UsuarioResponseDTO;
import cl.duocuc.ecomarket.tipodatos.Genero;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServicioUsuariosTest {

    @Mock
    private Encriptador<String> encriptador;

    @Mock
    private PersistenciaSP persistencia;

    @InjectMocks
    private ServicioUsuarios servicioUsuarios;


    @BeforeEach
    void setUp() {
        servicioUsuarios.init();
        servicioUsuarios.setPersistencia(persistencia);
    }

    @Test
    void registrarTest() {
        when(persistencia.agregarUsuario(
                "+56912345678",
                "Juan Perez",
                "juan.perez@example.com",
                "19900515",
                "Password123Hash",
                "M",
                "",
                "",
                "",
                "",
                2,
                1
        )).thenReturn(new UsuarioResponseDTO(1, 2, "Juan Perez"));


        when(encriptador.encriptar("Password123")).thenReturn("Password123Hash");


        UsuarioResponseDTO response = servicioUsuarios.registrar(getDTO());


        assertNotNull(response);
        assertEquals(1, response.getCodigo());
        assertEquals("Juan Perez", response.getDescripcion());
    }

    private ClienteRequestDTO getDTO() {
        return new ClienteRequestDTO(
                "+56912345678",
                "Juan Perez",
                "juan.perez@example.com",
                "19900515",
                "Password123",
                Genero.MASCULINO,
                2
        );
    }

}