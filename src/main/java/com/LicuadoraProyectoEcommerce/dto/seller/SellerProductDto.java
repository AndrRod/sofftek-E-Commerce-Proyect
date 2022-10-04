package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class SellerProductDto {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("final price")
    private Double finalPrice;
    @JsonProperty("seller creator email")
    private String sellerCreatorEmail;
}
