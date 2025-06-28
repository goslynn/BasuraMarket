package cl.duocuc.ecomarket.modelo.entity.venta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta", schema = "venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('venta.detalle_venta_id_detalle_venta_seq')")
    @Column(name = "id_detalle_venta", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta idVenta;

    @NotNull
    @JoinColumn(name = "id_producto", nullable = false)
    private Long idProducto;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @NotNull
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}