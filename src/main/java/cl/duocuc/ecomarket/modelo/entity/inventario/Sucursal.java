package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sucursal", schema = "empresa")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_sucursal", nullable = false, length = 100)
    private String nombreSucursal;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "idSucursal")
    private Set<Bodega> bodegas = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(Set<Bodega> bodegas) {
        this.bodegas = bodegas;
    }
}