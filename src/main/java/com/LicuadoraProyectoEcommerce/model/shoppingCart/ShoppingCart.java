package com.LicuadoraProyectoEcommerce.model.shoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity @AllArgsConstructor
public class ShoppingCart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "([A-Z]{1}[a-zØ-öø-ÿ]{2,})(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?(\\s[A-Z]{1}[a-zØ-öø-ÿ]{2,})?", message = "the name format is incorrect, and remember the names have to start with capital letters") //TODO PROBAR
    private String buyerName;
    @Pattern(regexp = "(?=.*[a-zA-Z_])(?!.*(\\s))(?!.*(\\_|@|\\-|\\.){2}).{4,25}@(gmail?|hotmail?|outlook?|yahoo?).(com{1})", message = "the email format is incorrect" +
            ", the only mail accounts allowed are: gmail, hotmail, outlook or yahoo.") //TODO PROBAR
    private String buyerEmail;
    @Pattern(regexp = "\\d{1,2}\\.\\d{3}\\.\\d{3}", message = "the dni format is incorrect dont forget to put the dots (example 33.064.279)")
    private String buyerDni;
    @Transient
    private Double finalPrice;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;
    public ShoppingCart(){
        this.productOrders = new ArrayList<>();
        this.finalPrice =  0d;
    }
    public ShoppingCart(String buyerName, String buyerEmail, String buyerDni){
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerDni = buyerDni;
    }

    public Double getFinalPrice() {
        if(productOrders ==null) return 0d;
        productOrders.stream().forEach(orderProduct -> {
              finalPrice += orderProduct.getFinalPricePerQuantity();});
        return finalPrice;
    }
}
