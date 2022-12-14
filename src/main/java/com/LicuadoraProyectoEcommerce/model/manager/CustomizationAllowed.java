package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor @Builder
public class CustomizationAllowed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "cant be null or empty")
    @Column(unique = true)
    private String type;
    @ManyToMany(mappedBy = "customizationsAllowed")
    private List<EnabledArea> enabledAreas;
    public CustomizationAllowed(String type){
        this.type = type;
    }
    public CustomizationAllowed(){
        this.enabledAreas= new ArrayList<>();
    }
}
