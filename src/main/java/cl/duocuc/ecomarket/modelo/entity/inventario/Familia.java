package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "familia", schema = "productos")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_familia", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_familia", nullable = false, length = 50)
    private String nombreFamilia;

    @Size(max = 50)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "idFamilia")
    private Set<Subfamilia> subfamilias = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<Subfamilia> getSubfamilias() {
        return subfamilias;
    }

    public void setSubfamilias(Set<Subfamilia> subfamilias) {
        this.subfamilias = subfamilias;
    }
}