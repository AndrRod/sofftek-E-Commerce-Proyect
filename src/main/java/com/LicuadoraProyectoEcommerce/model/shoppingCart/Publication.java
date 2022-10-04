package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Publication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "publication")
    private SellerProduct sellerProduct;
    @Enumerated(value = EnumType.STRING)
    private PublicationSate publicationSate;
    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate;
}
