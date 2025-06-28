package cl.duocuc.ecomarket.modelo.entity.usuario;

import cl.duocuc.ecomarket.modelo.entity.EntidadEcomarket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "permisos", schema = "usuario")
public class Permiso extends EntidadEcomarket implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "clave_permiso", nullable = false, length = 50)
    private String clavePermiso;

    @Column(name = "descripcion", length = 255)
    private String descripcion;


    @OneToMany(mappedBy = "permiso", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolesPermiso> rolesPermisos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClavePermiso() {
        return clavePermiso;
    }

    public void setClavePermiso(String clavePermiso) {
        this.clavePermiso = clavePermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<RolesPermiso> getRolesPermisos() {
        return rolesPermisos;
    }

    public void setRolesPermisos(Set<RolesPermiso> rolesPermisos) {
        this.rolesPermisos = rolesPermisos;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Permiso cloned = (Permiso) super.clone();
        cloned.setId(getId());
        cloned.setClavePermiso(getClavePermiso());
        cloned.setDescripcion(getDescripcion());
        cloned.setActivo(getActivo());
        cloned.setFechaCreacion(getFechaCreacion());
        cloned.setRolesPermisos(getRolesPermisos());
        return cloned;
    }
}
