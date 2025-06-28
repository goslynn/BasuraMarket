package cl.duocuc.ecomarket.modelo.repository;


import cl.duocuc.ecomarket.modelo.entity.inventario.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}