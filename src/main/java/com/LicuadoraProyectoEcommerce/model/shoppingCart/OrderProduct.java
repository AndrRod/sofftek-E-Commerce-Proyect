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
        this.finalPricePerQuantity = 1d;
        this.quantityOfProducts = 1;
    }
    public Double getFinalPricePerQuantity() {
        return quantityOfProducts*sellerProduct.getFinalPrice();
    }
}
