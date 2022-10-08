package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PurchaseDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "store name", example = "Andres Show Sport", type = "String", description = "cannot allow null")
    @JsonProperty("store name")
    private String storeName;
    @Schema(name = "payment method", example = "DEBIT_CARD")
    @JsonProperty("payment method")
    private PaymentMethod paymentMethod;
    @Schema(name = "number of transaction", example = "3234567892")
    @JsonProperty("number of transaction")
    private String numberOfTransaction;
    @Schema(name = "total purchase", example = "152000")
    @JsonProperty("total purchase")
    private Double totalPurchase;
    @Schema(name = "products", example = "[\n" +
            "        \"Name: zapatilla, Description: pelota con publicacion, Amount: 4, Final price: 8000.0\",\n" +
            "        \"Name: zapatilla, Description: zapatilla rosada para mujer, Amount: 1, Final price: 20000.0\"\n" +
            "    ]")
    private List<String> products = new ArrayList<>();
    @Schema(name = "buyer name", example = "andres comprador")
    @JsonProperty("buyer name")
    private String buyerName;
    @Schema(name = "buyer email", example = "andres@gmail.com")
    @JsonProperty("buyer email")
    private String buyerEmail;
    @Schema(name = "buyer name", example = "33.064.279")
    @JsonProperty("buyer dni")
    private String buyerDni;
    @Schema(name = "payment status", example = "ACCEPTED")
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
