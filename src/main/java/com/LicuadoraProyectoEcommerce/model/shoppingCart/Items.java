package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "sellerProduct_id", referencedColumnName = "id")
    private SellerProduct sellerProduct;

    private Integer quantityOfProducts;

    @Transient
    private Double finalPricePerQuantity;

    @CreationTimestamp
    @Column(name = "orderDate",updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime orderDate;
    public Items(){
        this.finalPricePerQuantity = 0d;
        this.quantityOfProducts = 1;
    }
    public Items(ShoppingCart shoppingCart, SellerProduct sellerProduct, Integer quantityOfProducts){
        this.shoppingCart = shoppingCart;
        this.sellerProduct = sellerProduct;
        this.quantityOfProducts = quantityOfProducts;
    }
    public Double getFinalPricePerQuantity() {
        Double price = sellerProduct.getFinalPrice();
        this.finalPricePerQuantity = 0d;
        finalPricePerQuantity += quantityOfProducts*Math.round(price);
        return finalPricePerQuantity;
    }
    public Integer getQuantityOfProducts(){
        if(quantityOfProducts==1 || quantityOfProducts ==0 || quantityOfProducts ==null) return this.quantityOfProducts= 1;
        return this.quantityOfProducts;
    };
}
