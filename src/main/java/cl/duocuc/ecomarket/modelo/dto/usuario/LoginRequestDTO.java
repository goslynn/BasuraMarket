package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.PeticionEncriptableDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;
import cl.duocuc.ecomarket.util.validacion.Contrasena;
import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.Email;


public record LoginRequestDTO(

        @Requerido
        @Email
        String correo,

        @Requerido
        @Contrasena
        String contrasena

) implements PeticionEncriptableDTO<Usuario> {

    @Override
    public Usuario toEntidad(Encriptador<String> enc) {
        Usuario usuario = new Usuario();
        usuario.setCorreoUsuario(correo);
        String passw;
        if (!enc.encriptado(contrasena)) {
            passw  = enc.encriptar(contrasena);
        } else {
            passw = contrasena;
        }
        usuario.setContrasenaHash(passw);
        return usuario;
    }
}
