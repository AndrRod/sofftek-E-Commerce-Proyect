package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SellerStoreCompleteDto {
    @Schema(name = "id", example = "1", hidden = true)
    private Long id;
    @Schema(name = "store name", example = "andres shopping sport")
    @JsonProperty("store name")
    private String name;
    @Schema(name = "store description", example = "tienda deportiva dedicada exclusivamente a vender calzado")
    @JsonProperty("store description")
    private String description;
    @JsonProperty("payment methods")
    private List<PaymentMethod> paymentMethods;
    @JsonProperty("publications")
    private List<PublicationDto> publications;
}
