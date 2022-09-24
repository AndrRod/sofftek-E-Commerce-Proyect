package com.LicuadoraProyectoEcommerce.model.seller;

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
public class SellerArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "areas")
    private List<SellerProduct> sellerProducts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinTable(name = "area_customization",
            joinColumns = @JoinColumn(name = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "customization_id"))
    private List<SellerCustomization> customizations;

    @ManyToOne
    @JoinColumn(name = "enabled_area_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EnabledArea enabledArea;
    public  SellerArea(){
        this.customizations= new ArrayList<>();
        this.sellerProducts = new ArrayList<>();
    }
    public  SellerArea(EnabledArea enabledArea){
        this.enabledArea = enabledArea;
    }

    public void removeProduct(SellerProduct sellerProduct){
        this.sellerProducts.remove(sellerProduct);
        sellerProduct.getAreas().remove(this);
    }
}