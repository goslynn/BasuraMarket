package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "subfamilia", schema = "productos")
public class Subfamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subfamilia", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_familia", nullable = false)
    private Familia idFamilia;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_subfamilia", nullable = false, length = 50)
    private String nombreSubfamilia;

    @Size(max = 50)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "idSubfamilia")
    private Set<Producto> productos = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Familia getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Familia idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombreSubfamilia() {
        return nombreSubfamilia;
    }

    public void setNombreSubfamilia(String nombreSubfamilia) {
        this.nombreSubfamilia = nombreSubfamilia;
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

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}