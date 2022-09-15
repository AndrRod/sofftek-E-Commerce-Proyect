package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @Entity
public class EnabledArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "enabledAreas")
    private List<BaseProduct> baseProducts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "enabledArea_customizationAllowed",
            joinColumns = @JoinColumn(name = "enableArea_id"),
            inverseJoinColumns = @JoinColumn(name = "customizationAllowed_id"))
    private List<CustomizationAllowed> customizationsAllowed;
    public EnabledArea(){

        this.baseProducts = new ArrayList<>();
    }
}
