package cl.duocuc.ecomarket.modelo.repository;

import cl.duocuc.ecomarket.modelo.entity.venta.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
