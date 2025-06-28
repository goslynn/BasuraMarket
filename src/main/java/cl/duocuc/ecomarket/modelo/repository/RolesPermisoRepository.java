package cl.duocuc.ecomarket.modelo.repository;

import cl.duocuc.ecomarket.modelo.entity.usuario.RolesPermiso;
import cl.duocuc.ecomarket.modelo.entity.usuario.RolesPermisoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesPermisoRepository extends JpaRepository<RolesPermiso, RolesPermisoId> {
    List<RolesPermiso> findAllByRol_Id(Integer idRol);
}
