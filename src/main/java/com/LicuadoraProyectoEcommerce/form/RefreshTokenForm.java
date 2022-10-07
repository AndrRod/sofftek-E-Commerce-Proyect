package com.LicuadoraProyectoEcommerce.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenForm {
    @Schema(name = "refresh_token", type = "String", example = "Bearer asdfasdfieowpjwefj89asdfjaspdfisadfjaiopsdfjiasodpfjaso")
    @NotBlank(message = "can't be null or empty")
    private String refresh_token;
}