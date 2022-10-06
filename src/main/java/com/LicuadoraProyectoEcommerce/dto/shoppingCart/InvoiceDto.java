package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
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
    @JsonProperty("payment status")
    private String status;
    @JsonProperty("invoice number")
    private String invoiceNumber;
    @JsonProperty("buyer name")
    private String buyerName;
    @JsonProperty("buyer email")
    private String buyerEmail;
    @JsonProperty("buyer dni")
    private String buyerDni;
    @JsonProperty("number of transaction")
    private String numberOfTransaction;
    @JsonProperty("payment method")
    private String paymentMethod;
    @JsonProperty("purchase list")
    private List<String> purchaseList = new ArrayList<>();
    @JsonProperty("total purchase")
    private Double totalPurchase;

    public InvoiceDto(Long id, PurchaseDto purchase, String invoiceNumber){
        this.id=id;
        this.invoiceNumber = invoiceNumber;
        this.status= purchase.getStatus();
        this.sellerStoreName= purchase.getStoreName();
        this.buyerDni= purchase.getBuyerDni();
        this.buyerEmail=purchase.getBuyerEmail();
        this.buyerName= purchase.getBuyerName();
        this.paymentMethod = String.valueOf(purchase.getPaymentMethod());
        this.numberOfTransaction = purchase.getNumberOfTransaction();
        this.totalPurchase= purchase.getTotalPurchase();
        this.purchaseList.addAll(purchase.getProducts());
    }
}
