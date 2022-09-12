package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class SellerCompleteDto {
    @NotBlank(message = "cant be empty or null")
    private String name;
    @NotBlank(message = "cant be empty or null")
    private String email;
    @NotBlank(message = "cant be empty or null")
    private String password;
}
