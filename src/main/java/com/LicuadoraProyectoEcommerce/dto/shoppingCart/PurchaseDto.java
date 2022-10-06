package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PurchaseDto {
    private Long id;
    @JsonProperty("store name")
    private String storeName;
    @JsonProperty("payment method")
    private PaymentMethod paymentMethod;
    @JsonProperty("number of transaction")
    private String numberOfTransaction;
    @JsonProperty("total purchase")
    private Double totalPurchase;
    private List<String> products = new ArrayList<>();
    @JsonProperty("buyer name")
    private String buyerName;
    @JsonProperty("buyer email")
    private String buyerEmail;
    @JsonProperty("buyer dni")
    private String buyerDni;
    @JsonProperty("payment status")
    private String status;
    public PurchaseDto(Long id, ShoppingCart shoppingCart, PaymentMethod paymentMethod, String numberOfTransaction, String status){
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.numberOfTransaction = numberOfTransaction;
        this.storeName= shoppingCart.getItems().get(0).getSellerProduct().getPublication().getStore().getName();
        this.buyerDni= shoppingCart.getBuyerDni();
        this.buyerEmail=shoppingCart.getBuyerEmail();
        this.buyerName= shoppingCart.getBuyerName();

        this.totalPurchase= shoppingCart.getFinalPrice();
        shoppingCart.getItems().stream().forEach(p->{
        this.products.add("Name: "+ p.getSellerProduct().getBaseProduct().getName()+
                    ", Description: " + p.getSellerProduct().getDescription() +
                    ", Amount: " + p.getQuantityOfProducts() +
                    ", Final price: " + p.getFinalPricePerQuantity());
        });
        this.status = status;
    }
}
