package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity @AllArgsConstructor
public class CustomizationAllowed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private Double price;
    @ManyToMany(mappedBy = "customizationsAllowed")
    private List<EnabledArea> enabledAreas;
    public CustomizationAllowed(){
        this.enabledAreas= new ArrayList<>();
    }
}
