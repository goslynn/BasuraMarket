package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;

public record UsuarioResponseDTO(
        Integer id,
        Integer idRol,
        String nombreUsuario
) implements RespuestaDTO {

    public static UsuarioResponseDTO fromEntidad(Usuario u) {
        return new UsuarioResponseDTO(
                u.getId(),
                u.getRol().getId(),
                u.getNombreUsuario()
        );
    }

    @Override
    public Integer getCodigo() {
        return id;
    }

    @Override
    public String getDescripcion() {
        return nombreUsuario;
    }

}
