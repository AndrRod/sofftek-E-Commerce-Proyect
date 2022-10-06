package com.LicuadoraProyectoEcommerce.repository.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    boolean existsByBuyerEmailOrBuyerDni(String buyerEmail, String buyerDni);
   List<ShoppingCart> findAllByShowEntity(boolean show, PageRequest of);
}
