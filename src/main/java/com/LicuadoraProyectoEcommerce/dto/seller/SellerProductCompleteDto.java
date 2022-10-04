package com.LicuadoraProyectoEcommerce.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SellerProductCompleteDto {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("base price")
    private Double basePrice;
    @JsonProperty("final price")
    private Double finalPrice;
    private List<SellerAreaCompleteDto> areas;
}
