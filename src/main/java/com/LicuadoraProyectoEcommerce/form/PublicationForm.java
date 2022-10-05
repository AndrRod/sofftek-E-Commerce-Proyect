package com.LicuadoraProyectoEcommerce.form;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class PublicationForm {
    @NotBlank(message = "can't be null or empty")
    private String description;
    @NotBlank(message = "can't be null or empty")
    private String publicationSate;
}
