package com.LicuadoraProyectoEcommerce.repository;

import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {
}
