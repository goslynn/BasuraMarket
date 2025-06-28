package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.usuario.UsuarioResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO getResponseDTO(Usuario u){
        return new UsuarioResponseDTO(
                u.getId(),
                u.getRol().getId(),
                u.getNombreUsuario()
        );
    }

}
