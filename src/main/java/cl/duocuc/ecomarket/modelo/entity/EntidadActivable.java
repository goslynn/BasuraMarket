package cl.duocuc.ecomarket.modelo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@MappedSuperclass
public class EntidadActivable implements Activable {

    @Column(name = "activo", insertable = false, nullable = false)
    @ColumnDefault("true")
    private Boolean activo;

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
        if (activo == null) {
            activo = true;
        }
    }

}
