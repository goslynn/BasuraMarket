package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;
import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RolPermisosResponseDTO(

        Integer id,

        @Requerido
        String nombre,

        @Requerido
        String descripcion,

        List<PermisoResponseDTO> permisos

) implements RespuestaDTO {

        public static RolPermisosResponseDTO fromEntidad(Rol r) {
                return new RolPermisosResponseDTO(
                        r.getId(),
                        r.getNombreRol(),
                        r.getDescripcion(),
                        r.getRolesPermisos().stream()
                                .map(rp -> PermisoResponseDTO.fromEntidad(rp.getPermiso()))
                                .toList()
                );
        }


        @Override
        public Integer getCodigo() {
                return null;
        }

        @Override
        public String getDescripcion() {
                return "";
        }


}

