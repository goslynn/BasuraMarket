package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.usuario.RolDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;

public class RolMapper {

    public static RolDTO toDTO(Rol r){
        return new RolDTO(
                r.getId(),
                r.getNombreRol(),
                r.getDescripcion()
        );
    }
}
