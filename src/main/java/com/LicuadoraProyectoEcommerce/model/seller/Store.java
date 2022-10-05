package com.LicuadoraProyectoEcommerce.model.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Data @Entity @AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ElementCollection
    @CollectionTable(name = "payment_methods", joinColumns = @JoinColumn(name = "store_id"))
    @Enumerated(value = EnumType.STRING)
    private List<PaymentMethod> paymentMethods;

    @OneToOne(mappedBy = "store", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Seller seller;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY ,orphanRemoval = true)
    private List<Publication> publication;
    public Store(){
        this.paymentMethods = new ArrayList<>();
    }
    public Store(String name, String description){
        this.name = name;
        this.description = description;
    }
    @PreRemove
    public void sellerRemoval() {
        this.getSeller().setStore(null);
    }
}
