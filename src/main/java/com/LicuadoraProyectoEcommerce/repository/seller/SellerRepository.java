package com.LicuadoraProyectoEcommerce.repository.seller;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUser(User user);
}
