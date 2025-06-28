package cl.duocuc.ecomarket.control;

import cl.duocuc.ecomarket.modelo.dto.usuario.LoginRequestDTO;
import cl.duocuc.ecomarket.servicio.ServicioAuth;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Auth.PATH)
public class Auth {

    protected static final String PATH = "/api/v1/public/auth";
    private final ServicioAuth auth;


    protected Auth (ServicioAuth authService) {
        this.auth = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<CodigoDescripcion<Integer, String>> login(@RequestBody @Valid LoginRequestDTO dto){
        return ResponseEntity.ok(auth.login(dto));
    }

    //TODO: Refresh Token ?

}
