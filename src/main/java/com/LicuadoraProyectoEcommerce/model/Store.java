package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data @Entity @AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(name = "payment_methods", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "payment_method_name")
    private List<String> paymentMethods;
    @OneToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<SellerProduct> sellerProducts;
    public Store(){
        this.sellerProducts = new ArrayList<>();
        this.paymentMethods = new ArrayList<>();
    }
    private void addNewsPaymentMethods(String ... newPayMethods){
        Collections.addAll(this.paymentMethods, newPayMethods);}
}
