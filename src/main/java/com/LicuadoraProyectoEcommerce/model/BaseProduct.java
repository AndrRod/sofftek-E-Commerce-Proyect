package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data @AllArgsConstructor
public class BaseProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer daysToManufacture;
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "baseProduct_enableArea",
            joinColumns = @JoinColumn(name = "baseProduct_id"),
            inverseJoinColumns = @JoinColumn(name = "enableArea_id"))
    private List<EnabledArea> enabledAreas;
    public BaseProduct(){
        this.enabledAreas = new ArrayList<>();
    }

    public void addAreaToBaseProduct(EnabledArea enabledArea) {
        enabledAreas.add(enabledArea);
    }
    public void removeAreaToBaseProduct(EnabledArea enabledArea) {
        enabledAreas.remove(enabledArea);
    }
}
