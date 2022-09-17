package com.LicuadoraProyectoEcommerce.repository;

import com.LicuadoraProyectoEcommerce.model.EnabledArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnableAreaRepository extends JpaRepository<EnabledArea, Long> {
    Optional<EnabledArea> findByName(String name);
}
