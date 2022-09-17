package com.LicuadoraProyectoEcommerce.repository.manager;

import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUser(User user);
}
