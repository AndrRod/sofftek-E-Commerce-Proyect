package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor
public class CustomizationAllowed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "cant be null or empty")
    @Column(unique = true)
    private String type;
    @ManyToMany(mappedBy = "customizationsAllowed")
    private List<EnabledArea> enabledAreas;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerCustomization> sellerCustomizations;
    public CustomizationAllowed(String type){
        this.type = type;
    }
    public CustomizationAllowed(){
        this.enabledAreas= new ArrayList<>();
        this.sellerCustomizations = new ArrayList<>();
    }
}
