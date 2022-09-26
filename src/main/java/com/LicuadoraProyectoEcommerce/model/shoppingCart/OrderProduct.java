package com.LicuadoraProyectoEcommerce.model.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("shoppingCart_id")
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;
    @ManyToOne
    @MapsId("sellerProduct_id")
    @JoinColumn(name = "sellerProduct_id", referencedColumnName = "id")
    private SellerProduct sellerProduct;

    private Integer quantityOfProducts;
    @Transient
    private Double finalPricePerQuantity;

    public OrderProduct(){
        this.finalPricePerQuantity = 1d;
        this.quantityOfProducts = 1;
    }
    public Double getFinalPricePerQuantity() {
        return quantityOfProducts*sellerProduct.getFinalPrice();
    }
}
