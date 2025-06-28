package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.usuario.PermisoResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;

public class PermisoMapper {

    public static PermisoResponseDTO toResponseDTO(Permiso p){
        return new PermisoResponseDTO(
                p.getId(),
                p.getClavePermiso(),
                p.getDescripcion()
        );
    }




}
