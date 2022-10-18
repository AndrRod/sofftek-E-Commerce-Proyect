package com.LicuadoraProyectoEcommerce.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceForm {
    @Schema(name = "invoice number", example = "4569802369", type = "String")
    @JsonProperty("invoice number")
    @Pattern(regexp = "[0-9]{10}", message = "only accepts 10 numbers")
    private String invoiceNumber;
    @NotBlank(message = "can't be null or empty")
        @Schema(name = "select disk to save", example = "d", type = "String")
        @JsonProperty("select disk to save")
        @Pattern(regexp = "[a-zA-Z]{1}", message = "you can only select a disk rute valid, (example: d)")
        private String selectDiskToSave;
}
