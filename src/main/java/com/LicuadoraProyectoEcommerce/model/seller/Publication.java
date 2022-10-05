package com.LicuadoraProyectoEcommerce.model.seller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @Entity @NoArgsConstructor
public class Publication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sellerProduct_id", referencedColumnName = "id")
    private SellerProduct sellerProduct;

    private String description;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @Enumerated(value = EnumType.STRING)
    private PublicationSate publicationSate;
    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate;
    public Publication(SellerProduct sellerProduct, String description, PublicationSate publicationSate){
        this.sellerProduct= sellerProduct;
        this.description = description;
        this.publicationSate = publicationSate;
    }
    @PreRemove
    public void sellerProductRemoval() {
        this.getSellerProduct().setPublication(null);
    }
}
