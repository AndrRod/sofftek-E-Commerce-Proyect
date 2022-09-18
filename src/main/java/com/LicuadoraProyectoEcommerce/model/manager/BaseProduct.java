package com.LicuadoraProyectoEcommerce.model.manager;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.LAZY) //TODO
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

    public void removeAreasToBaseProduct(List<EnabledArea> enabledAreas) {
        this.enabledAreas.removeAll(enabledAreas);
    }
}
