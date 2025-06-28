package cl.duocuc.ecomarket.control.usuario;



import cl.duocuc.ecomarket.modelo.dto.usuario.UsuarioResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.usuario.EmpleadoRequestDTO;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/private/user")
public class PrivateUsuarioController {
    private final ServicioUsuarios service;
    private static final Logger log = LoggerFactory.getLogger(PrivateUsuarioController.class);

    public PrivateUsuarioController(ServicioUsuarios service) {
        this.service = service;
    }


    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponseDTO> crear(@Validated @RequestBody EmpleadoRequestDTO usuario){
        return ResponseEntity.status(201).body(service.registrar(usuario));
    }

}
