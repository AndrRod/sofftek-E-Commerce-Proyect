package com.LicuadoraProyectoEcommerce.repository.seller;

import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerCustomizationRepository extends JpaRepository<SellerCustomization, Long> {
}
