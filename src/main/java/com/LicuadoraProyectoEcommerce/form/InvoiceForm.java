package com.LicuadoraProyectoEcommerce.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceForm {
    @JsonProperty("invoice number")
    @Pattern(regexp = "[0-9]{10}", message = "only accepts 10 numbers")
    private String invoiceNumber;
    @NotBlank(message = "can't be null or empty")
    private String status;
}
