package cl.duocuc.ecomarket.modelo.entity.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "bodega", schema = "empresa")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_bodega", nullable = false, length = 100)
    private String nombreBodega;

    @ColumnDefault("true")
    @Column(name = "activa")
    private Boolean activa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal idSucursal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }
}