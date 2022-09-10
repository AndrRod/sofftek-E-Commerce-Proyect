package com.LicuadoraProyectoEcommerce.repository;

import com.LicuadoraProyectoEcommerce.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);
}
