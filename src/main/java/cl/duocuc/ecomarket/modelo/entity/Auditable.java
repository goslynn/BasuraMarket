package cl.duocuc.ecomarket.modelo.entity;

import java.time.Instant;

public interface Auditable {
    Instant getFechaCreacion();
    void setFechaCreacion(Instant fechaCreacion);
}
