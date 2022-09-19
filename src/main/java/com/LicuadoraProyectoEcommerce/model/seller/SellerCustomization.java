package com.LicuadoraProyectoEcommerce.model.seller;

import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class SellerCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double customizationPrice;
    @ManyToMany(mappedBy = "customizations")
    private List<SellerArea> areas;

    @ManyToOne
    @JoinColumn(name = "customization_allowed_id")
    private CustomizationAllowed customizationAllowed;
    public SellerCustomization(){
        this.areas = new ArrayList<>();
        this.customizationPrice = 0d;
    }
}
