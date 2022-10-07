package com.LicuadoraProyectoEcommerce.form;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class PublicationForm {
    @Schema(name = "description", type = "String", example = "Esta publicación es una oferta de la temporada")
    @NotBlank(message = "can't be null or empty")
    private String description;
    @Schema(name = "publication state", type = "String", example = "PUBLISHED")
    @NotBlank(message = "can't be null or empty")
    @JsonProperty("publication state")
    private String publicationSate;
}
