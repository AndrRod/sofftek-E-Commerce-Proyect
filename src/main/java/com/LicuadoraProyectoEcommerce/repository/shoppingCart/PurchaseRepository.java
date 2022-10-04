package com.LicuadoraProyectoEcommerce.repository.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsByShoppingCart(ShoppingCart shoppingCart);
}
