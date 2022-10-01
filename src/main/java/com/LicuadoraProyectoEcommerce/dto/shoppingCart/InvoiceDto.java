package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InvoiceDto {
    private Long id;
    @JsonProperty("store name")
    private String sellerStoreName;
    @JsonProperty("buyer name")
    private String buyerName;
    @JsonProperty("buyer email")
    private String buyerEmail;
    @JsonProperty("buyer dni")
    private String buyerDni;
    @JsonProperty("number of transaction")
    private String numberOfTransaction;
    @JsonProperty("total purchase")
    private Double totalPurchase;
    @JsonProperty("buyer products")
    private List<String> products = new ArrayList<>();
    @JsonProperty("payment status")
    private StatusPayment status;
    @JsonProperty("invoice number")
    private String invoiceNumber;
}
