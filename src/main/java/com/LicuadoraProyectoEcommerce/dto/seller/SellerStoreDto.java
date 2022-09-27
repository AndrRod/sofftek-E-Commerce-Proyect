package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@AllArgsConstructor
public class SellerStoreDto {
    @NotBlank(message = "cant be empty or null")
    private String name;
    @NotBlank(message = "cant be empty or null")
    private String description;
}
