package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
public class OrderProduct {
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
    private LocalDateTime orderDate;
    public OrderProduct(){
        this.finalPricePerQuantity = 0d;
        this.quantityOfProducts = 1;
    }
    public OrderProduct(ShoppingCart shoppingCart, SellerProduct sellerProduct, Integer quantityOfProducts){
        this.shoppingCart = shoppingCart;
        this.sellerProduct = sellerProduct;
        this.quantityOfProducts = quantityOfProducts;
    }
    public Double getFinalPricePerQuantity() {
        this.finalPricePerQuantity = 0d;
        Double price = sellerProduct.getFinalPrice();
        finalPricePerQuantity = (quantityOfProducts==1 || quantityOfProducts ==0)? price: quantityOfProducts*Math.round(price);
        return finalPricePerQuantity;
    }
    public Integer getQuantityOfProducts(){
        if(quantityOfProducts==null || quantityOfProducts == 0) return this.quantityOfProducts= 1;
        return this.quantityOfProducts;
    };
}
