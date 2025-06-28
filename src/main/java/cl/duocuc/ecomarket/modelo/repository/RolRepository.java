package cl.duocuc.ecomarket.modelo.repository;

import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {}

