package com.LicuadoraProyectoEcommerce.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class productOrderForm {
    @JsonProperty("quantity of products")
    private Integer quantityOfProducts;
}
