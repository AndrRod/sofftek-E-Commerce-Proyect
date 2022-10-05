package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class PublicationDto {
    private Long id;
    @JsonProperty("product name")
    private String productName;
    @JsonProperty("product description")
    private String descriptionProduct;
    @JsonProperty("description")
    private String description;
    @JsonProperty("store name")
    private String storeName;
    @JsonProperty("publication state")
    private PublicationSate publicationState;
    @JsonProperty("last update state")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate;
}
