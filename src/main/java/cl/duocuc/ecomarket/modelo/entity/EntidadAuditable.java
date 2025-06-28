package cl.duocuc.ecomarket.modelo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@MappedSuperclass
public class EntidadAuditable implements Auditable{

    @Column(name = "fecha_creacion", insertable = false, updatable = false, nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Instant fechaCreacion;


    @Override
    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void initDefaults() {
        if (fechaCreacion == null) {
            fechaCreacion = Instant.now();
        }
    }
}
