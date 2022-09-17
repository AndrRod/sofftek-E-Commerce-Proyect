package com.LicuadoraProyectoEcommerce.repository.seller;

import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAreaRepository extends JpaRepository<SellerArea, Long> {
}
