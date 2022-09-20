package com.LicuadoraProyectoEcommerce.model.seller;

import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
public class SellerArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "areas")
    private List<SellerProduct> sellerProducts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "area_customization",
    joinColumns = @JoinColumn(name = "area_id"),
    inverseJoinColumns = @JoinColumn(name = "customization_id"))
    private List<SellerCustomization> customizations;

    @ManyToOne
    @JoinColumn(name = "enabled_area_id", referencedColumnName = "id")
    private EnabledArea enabledArea;
    public  SellerArea(){
        this.customizations= new ArrayList<>();
        this.sellerProducts = new ArrayList<>();
    }
    public  SellerArea(EnabledArea enabledArea){
        this.enabledArea = enabledArea;
    }

    public void addCustomizationToSellerArea(SellerCustomization sellerCustomization) {
        customizations.add(sellerCustomization);
    }
    public void removeCustomizationToSellerArea(SellerCustomization sellerCustomization) {
        customizations.remove(sellerCustomization);
    }
}
