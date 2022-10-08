package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class PublicationDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "store name", example = "andres shopping sport")
    @JsonProperty("store name")
    private String storeName;
    @Schema(name = "product name", example = "zapatilla adidas modelo n1")
    @JsonProperty("product name")
    private String productName;
    @Schema(name = "product description", example = "zapatilla deporitva de color blanca tamaño 39 apta para jugar al tenis")
    @JsonProperty("product description")
    private String productDescription;
    @Schema(name = "product price", example = "20.000")
    @JsonProperty("product price")
    private Double productPrice;
    @Schema(name = "publication description", example = "Super oferta de verano, valor del producto valido solo por compras online")
    @JsonProperty("publication description")
    private String publicationDescription;
    @Schema(name = "publication description", example = "PUBLISHED")
    @JsonProperty("publication state")
    private PublicationSate publicationState;
    @Schema(name = "last update state", example = "2022-05-22 22:01:22")
    @JsonProperty("last update state")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate;
}
