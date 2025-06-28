package cl.duocuc.ecomarket.modelo.entity.usuario;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolesPermisoId implements Serializable {
    private Integer idRol;
    private Integer idPermiso;

    public RolesPermisoId() {
    }

    public RolesPermisoId(Integer idRol, Integer idPermiso) {
        this.idRol = idRol;
        this.idPermiso = idPermiso;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesPermisoId that = (RolesPermisoId) o;
        return Objects.equals(idRol, that.idRol) && Objects.equals(idPermiso, that.idPermiso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idPermiso);
    }
}
