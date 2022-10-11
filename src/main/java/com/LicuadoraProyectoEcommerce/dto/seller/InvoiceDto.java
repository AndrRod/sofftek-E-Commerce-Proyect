package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InvoiceDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "store name", example = "Andres Show Sport", type = "String", description = "cannot allow null")
    @JsonProperty("store name")
    private String sellerStoreName;
    @Schema(name = "payment status", example = "PENDING")
    @JsonProperty("payment status")
    private String status;
    @Schema(name = "invoice number", example = "1234567891")
    @JsonProperty("invoice number")
    private String invoiceNumber;
    @Schema(name = "buyer name", example = "Federico Romero")
    @JsonProperty("buyer name")
    private String buyerName;
    @Schema(name = "buyer email", example = "Federico_Comprador@gmail.com")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @Schema(name = "buyer dni", example = "32.538.979")
    @JsonProperty("buyer dni")
    private String buyerDni;
    @Schema(name = "number of transaction", example = "3234567892")
    @JsonProperty("number of transaction")
    private String numberOfTransaction;
    @Schema(name = "payment method", example = "DEBIT_CARD")
    @JsonProperty("payment method")
    private String paymentMethod;
    @Schema(name = "purchase list", example = "[\n" +
            "        \"Name: zapatilla, Description: pelota con publicacion, Amount: 4, Final price: 8000.0\",\n" +
            "        \"Name: zapatilla, Description: zapatilla rosada para mujer, Amount: 1, Final price: 20000.0\"\n" +
            "    ]")
    @JsonProperty("purchase list")
    private List<String> purchaseList = new ArrayList<>();
    @Schema(name = "total purchase", example = "152000")
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
