package com.LicuadoraProyectoEcommerce.repository.seller;

import com.LicuadoraProyectoEcommerce.model.seller.Publication;
import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByPublicationSate(PublicationSate publicationSate, PageRequest pageRequest);
    boolean existsBySellerProduct(SellerProduct sellerProduct);
}
