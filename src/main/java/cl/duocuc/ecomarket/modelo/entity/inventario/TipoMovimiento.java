package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_movimiento", schema = "inventario")
public class TipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimiento", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_tipo_movimiento", nullable = false, length = 100)
    private String nombreTipoMovimiento;

    @Size(max = 100)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "idTipoMovimiento")
    private Set<Movimiento> movimientos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTipoMovimiento() {
        return nombreTipoMovimiento;
    }

    public void setNombreTipoMovimiento(String nombreTipoMovimiento) {
        this.nombreTipoMovimiento = nombreTipoMovimiento;
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

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

}