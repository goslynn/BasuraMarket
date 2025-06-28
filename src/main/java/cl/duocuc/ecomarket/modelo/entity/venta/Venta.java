package cl.duocuc.ecomarket.modelo.entity.venta;

import cl.duocuc.ecomarket.modelo.entity.EntidadEcomarket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "venta", schema = "venta")
public class Venta extends EntidadEcomarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('venta.venta_id_venta_seq')")
    @Column(name = "id_venta", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @NotNull
    @Column(name = "id_estado_venta", nullable = false)
    private Integer idEstadoVenta;

    @NotNull
    @Column(name = "id_tipo_documento", nullable = false)
    private Integer idTipoDocumento;

    @NotNull
    @Column(name = "total_neto", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalNeto;

    @NotNull
    @Column(name = "total_iva", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalIva;

    @NotNull
    @Column(name = "total_bruto", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalBruto;

    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;

    @NotNull
    @Column(name = "id_forma_pago", nullable = false)
    private Integer idFormaPago;

    @OneToMany(mappedBy = "idVenta")
    private Set<DetalleVenta> detalleVentas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public BigDecimal getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }

    public BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(BigDecimal totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public Integer getIdEstadoVenta() {
        return idEstadoVenta;
    }

    public void setIdEstadoVenta(Integer idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }
}