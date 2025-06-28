package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "inventario", schema = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "id_bodega", nullable = false)
    private Integer idBodega;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto idProducto;

    @NotNull
    @Column(name = "stock_actual", nullable = false, columnDefinition = "integer default 0")
    private Integer stockActual = 0;

    @Column(name = "stock_minimo", columnDefinition = "integer default 0")
    private Integer stockMinimo = 0;

    @Column(name = "stock_maximo", columnDefinition = "integer default 0")
    private Integer stockMaximo = 0;

    @Column(name = "activo", nullable = false, columnDefinition = "boolean default true")
    private Boolean activo = true;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Integer idBodega) {
        this.idBodega = idBodega;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
