package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "producto", schema = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "codigo_producto", nullable = false, length = 100)
    private String codigoProducto;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombreProducto;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_subfamilia", nullable = false)
    private Subfamilia idSubfamilia;

    @Column(name = "img")
    private byte[] img;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "idProducto")
    private Set<Inventario> inventarios = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Subfamilia getIdSubfamilia() {
        return idSubfamilia;
    }

    public void setIdSubfamilia(Subfamilia idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(Set<Inventario> inventarios) {
        this.inventarios = inventarios;
    }
}