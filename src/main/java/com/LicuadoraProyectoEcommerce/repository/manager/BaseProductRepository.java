package com.LicuadoraProyectoEcommerce.repository.manager;

import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {
}
