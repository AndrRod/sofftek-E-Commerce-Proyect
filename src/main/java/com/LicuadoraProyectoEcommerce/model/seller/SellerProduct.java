package com.LicuadoraProyectoEcommerce.model.seller;


import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class SellerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Double finalPrice;
    private Double basePrice;
    @ManyToOne
    @JoinColumn(name = "base_product_id", referencedColumnName = "id")
    private BaseProduct baseProduct;
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sellerProduct_area",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<SellerArea> areas = new ArrayList<>();
    public SellerProduct(Double basePrice, BaseProduct baseProduct, Seller seller){
        this.basePrice = basePrice;
        this.baseProduct=baseProduct;
        this.seller=seller;
    }
    public SellerProduct(){
    }
    public void addAreaToSellerProduct(SellerArea sellerArea) {
        areas.add(sellerArea);
    }
    public void removeAreaToSellerProduct(SellerArea sellerArea) {
        areas.remove(sellerArea);
    }
}
