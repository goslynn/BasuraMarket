package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;

public record RolDTO(
        Integer id,
        String nombre,
        String descripcion
) implements PeticionDTO<Rol>, RespuestaDTO {

    public static RolDTO fromEntidad(Rol rol) {
        return new RolDTO(
                rol.getId(),
                rol.getNombreRol(),
                rol.getDescripcion()
        );
    }

    @Override
    public Rol toEntidad() {
        Rol r = new Rol();
        r.setId(id());
        r.setNombreRol(nombre());
        r.setDescripcion(descripcion());
        return r;
    }


    @Override
    public Integer getCodigo() {
        return id();
    }

    @Override
    public String getDescripcion() {
        return String.format("%s - %s", nombre(), descripcion());
    }
}
