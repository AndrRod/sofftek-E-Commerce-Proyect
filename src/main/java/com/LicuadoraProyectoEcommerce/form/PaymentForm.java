package com.LicuadoraProyectoEcommerce.form;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter @AllArgsConstructor
public class PaymentForm {
    @JsonProperty("payment method")
    @NotBlank(message = "can't be null or empty")
    private String paymentMethod;
    @JsonProperty("number of transaction")
    @NotBlank(message = "can't be null or empty")
    @Pattern(regexp = "[0-9]{10}", message = "only accepts 10 numbers")
    private String numberOfTransaction;
}
