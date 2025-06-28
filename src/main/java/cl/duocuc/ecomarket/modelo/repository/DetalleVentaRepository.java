package cl.duocuc.ecomarket.modelo.repository;

import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
