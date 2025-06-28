package cl.duocuc.ecomarket.modelo.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@MappedSuperclass
public class EntidadEcomarket implements Activable, Auditable {

    @Column(name = "fecha_creacion", insertable = false, updatable = false, nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Instant fechaCreacion;

    @Column(name = "activo", insertable = false, nullable = false)
    @ColumnDefault("true")
    private Boolean activo;

    @Override
    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public Boolean getActivo() {
        return activo;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void initDefaults() {
        if (fechaCreacion == null) {
            fechaCreacion = Instant.now();
        }
        if (activo == null) {
            activo = true;
        }
    }

}
