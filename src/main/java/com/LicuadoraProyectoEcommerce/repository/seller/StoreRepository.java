package com.LicuadoraProyectoEcommerce.repository.seller;

import com.LicuadoraProyectoEcommerce.model.seller.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
