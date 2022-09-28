package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor
public class BaseProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double estimatedPrice;
    private Integer daysToManufacture;
    @OneToMany(mappedBy = "baseProduct", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerProduct> sellerProducts;
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.LAZY)
    @JoinTable(
            name = "baseProduct_enableArea",
            joinColumns = @JoinColumn(name = "base_product_id"),
            inverseJoinColumns = @JoinColumn(name = "enabled_area_id"))
    private List<EnabledArea> enabledAreas;
    public BaseProduct(){
        this.enabledAreas = new ArrayList<>();
    }
    public BaseProduct(String name, Double estimatedPrice, Integer daysToManufacture, List<EnabledArea> enabledAreas){
        this.name = name;
        this.estimatedPrice = estimatedPrice;
        this.daysToManufacture = daysToManufacture;
        this.enabledAreas=enabledAreas;

    }
    public void addAreaToBaseProduct(EnabledArea enabledArea) {
        enabledAreas.add(enabledArea);
        enabledArea.getBaseProducts().add(this);
    }
    public void removeAreaToBaseProduct(EnabledArea enabledArea) {
        enabledAreas.remove(enabledArea);
        enabledArea.getBaseProducts().remove(this);
    }

    public void removeAreasToBaseProduct(List<EnabledArea> enabledAreas) {
        this.enabledAreas.removeAll(enabledAreas);
    }
}
