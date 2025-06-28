package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "movimiento", schema = "inventario")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_tipo_movimiento", nullable = false)
    private TipoMovimiento idTipoMovimiento;

    @Column(name = "usuario_responsable")
    private Integer usuarioResponsable;

    @Size(max = 100)
    @Column(name = "motivo", length = 100)
    private String motivo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaMovimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public TipoMovimiento getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(TipoMovimiento idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Integer getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Integer usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Instant getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Instant fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

}