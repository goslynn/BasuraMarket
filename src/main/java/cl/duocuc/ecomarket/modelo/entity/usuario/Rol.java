package cl.duocuc.ecomarket.modelo.entity.usuario;

import cl.duocuc.ecomarket.modelo.entity.EntidadEcomarket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "usuario")
public class Rol extends EntidadEcomarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nombre_rol", nullable = false, length = 50)
    private String nombreRol;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolesPermiso> rolesPermisos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
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

}