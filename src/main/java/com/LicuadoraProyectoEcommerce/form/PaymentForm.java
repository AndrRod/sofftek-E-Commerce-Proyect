package com.LicuadoraProyectoEcommerce.form;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter @AllArgsConstructor
public class PaymentForm {
    @Schema(name = "payment method", example = "CREDIT_CARD", type = "String")
    @JsonProperty("payment method")
    @NotBlank(message = "can't be null or empty")
    private String paymentMethod;
    @Schema(name = "number of transaction", example = "4569802369", type = "String")
    @JsonProperty("number of transaction")
    @NotBlank(message = "can't be null or empty")
    @Pattern(regexp = "[0-9]{10}", message = "only accepts 10 numbers")
    private String numberOfTransaction;
}
