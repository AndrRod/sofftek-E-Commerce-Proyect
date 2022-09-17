package com.LicuadoraProyectoEcommerce.repository.seller;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerProductRepository extends JpaRepository<SellerProduct, Long> {
}
