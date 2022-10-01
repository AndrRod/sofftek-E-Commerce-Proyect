package com.LicuadoraProyectoEcommerce.repository.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.Invoice;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsByPayment(Payment payment);
}
