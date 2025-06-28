package cl.duocuc.ecomarket.modelo.entity.usuario;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "roles_permisos", schema = "usuario")
public class RolesPermiso {
    @EmbeddedId
    private RolesPermisoId id;

    @MapsId("idRol")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @MapsId("idPermiso")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_permiso", nullable = false)
    private Permiso permiso;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant asignadoEl = Instant.now();


    public RolesPermisoId getId() {
        return id;
    }

    public void setId(RolesPermisoId id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Instant getAsignadoEl() {
        return asignadoEl;
    }

    public void setAsignadoEl(Instant asignadoEl) {
        this.asignadoEl = asignadoEl;
    }
}
