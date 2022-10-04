package com.LicuadoraProyectoEcommerce.repository.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<Items, Long> {
}
