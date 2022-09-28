package com.LicuadoraProyectoEcommerce.model.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Data @Entity @AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(name = "payment_methods", joinColumns = @JoinColumn(name = "store_id"))
    @Enumerated(value = EnumType.STRING)
    private List<PaymentMethod> paymentMethods;
    @OneToOne(mappedBy = "store")
    private Seller seller;
    @OneToMany(mappedBy = "store")
    private List<SellerProduct> sellerProducts;
    public Store(){
        this.sellerProducts = new ArrayList<>();
        this.paymentMethods = new ArrayList<>();
    }
    public Store(String name, String description){
        this.name = name;
        this.description = description;
    }
}
