package com.LicuadoraProyectoEcommerce.dto.seller;

import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SellerStoreCompleteDto {
    private Long id;
    @JsonProperty("store name")
    private String name;
    @JsonProperty("store description")
    private String description;
    @JsonProperty("payment methods")
    private List<PaymentMethod> paymentMethods;
    @JsonProperty("publications")
    private List<PublicationDto> publications;
}
