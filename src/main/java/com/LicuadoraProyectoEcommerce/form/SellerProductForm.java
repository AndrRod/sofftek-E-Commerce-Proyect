package com.LicuadoraProyectoEcommerce.form;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class SellerProductForm {
    @NotBlank(message = "can't be null or empty")
    @JsonProperty("base price")
    private Double basePrice;
    @NotBlank(message = "can't be null or empty")
    private String description;
}
