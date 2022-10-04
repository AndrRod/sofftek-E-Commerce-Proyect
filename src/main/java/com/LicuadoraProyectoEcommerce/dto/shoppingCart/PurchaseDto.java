package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
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
}
