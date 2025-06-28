package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;
import cl.duocuc.ecomarket.modelo.entity.usuario.RolesPermiso;
import cl.duocuc.ecomarket.util.validacion.Requerido;

import java.util.HashSet;
import java.util.List;

public record RolPermisosRequestDTO(
        @Requerido
        String nombre,

        @Requerido
        String descripcion,

        List<Integer> permisos
) implements PeticionDTO<Rol> {

        @Override
        public Rol toEntidad() {
                Rol r = new Rol();
                r.setNombreRol(nombre);
                r.setDescripcion(descripcion);
                r.setRolesPermisos(
                        new HashSet<>(
                                permisos.stream()
                                        .map(idPermiso -> {
                                                RolesPermiso rp = new RolesPermiso();
                                                rp.setRol(r);
                                                Permiso p = new Permiso();
                                                p.setId(idPermiso);
                                                rp.setPermiso(p);
                                                return rp;
                                        }).toList()
                        )
                );
                return r;
        }

}
