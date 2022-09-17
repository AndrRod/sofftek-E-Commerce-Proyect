package com.LicuadoraProyectoEcommerce.repository;

import com.LicuadoraProyectoEcommerce.model.CustomizationAllowed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomizationAllowedRepository extends JpaRepository<CustomizationAllowed, Long> {
    Optional<CustomizationAllowed> findByType(String type);
}
