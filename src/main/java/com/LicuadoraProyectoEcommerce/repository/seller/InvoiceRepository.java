package com.LicuadoraProyectoEcommerce.repository.seller;

import com.LicuadoraProyectoEcommerce.model.seller.Invoice;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsByPurchase(Purchase purchase);
}
