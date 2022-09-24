package com.LicuadoraProyectoEcommerce.model.seller;

import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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
    @JoinColumn(name = "customization_allowed_id" ,referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomizationAllowed customizationAllowed;
    public SellerCustomization(){
        this.areas = new ArrayList<>();
        this.customizationPrice = 0d;
    }
    public void removeArea(SellerArea sellerArea){
//        this.areas.remove(sellerArea);
        sellerArea.getCustomizations().remove(this);
    }
    public void removeAllAreas(){
        this.areas.stream().forEach(area->{
            removeArea(area);
        });
    }
}
