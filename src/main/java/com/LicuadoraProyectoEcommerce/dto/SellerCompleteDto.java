package com.LicuadoraProyectoEcommerce.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class SellerCompleteDto {
    @NotBlank(message = "cant be empty or null")
    private String name;
    @NotBlank(message = "cant be empty or null")
    private String email;
    @NotBlank(message = "cant be empty or null")
    private String password;
}
