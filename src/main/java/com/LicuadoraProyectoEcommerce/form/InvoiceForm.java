package com.LicuadoraProyectoEcommerce.form;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceForm {
    @NotBlank(message = "can't be null or empty")
    @JsonProperty("invoice number")
    private String invoiceNumber;
}
