package com.LicuadoraProyectoEcommerce.repository.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
