package com.LicuadoraProyectoEcommerce.model.seller;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ProductOrder;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BaseProduct baseProduct;
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinTable(name = "sellerProduct_area",
            joinColumns = @JoinColumn(name = "product_seller_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_area_id"))
    private List<SellerArea> areas = new ArrayList<>();


    @OneToMany(mappedBy = "sellerProduct", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;
    public SellerProduct(Double basePrice, BaseProduct baseProduct, String description){
        this.basePrice = basePrice;
        this.baseProduct=baseProduct;
        this.description= description;
    }
    public SellerProduct(){
        this.finalPrice = 0d;
        this.productOrders = new ArrayList<>();
    }
    public void addAreaToSellerProduct(SellerArea sellerArea) {
        areas.add(sellerArea);
    }
    public Double getFinalPrice() {
        if(this.finalPrice==null) return this.basePrice;
        this.finalPrice = 0d;
        areas.stream().forEach(areas -> {
            this.finalPrice += areas.getCustomizations().stream().mapToDouble(SellerCustomization::getCustomizationPrice).sum();
        });
        return finalPrice + this.basePrice;
    }
}
