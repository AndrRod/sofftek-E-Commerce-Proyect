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
//    @ManyToMany(mappedBy = "enabledAreaList")
//    private List<BaseProduct> productList;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomizationAllowed> customizationAlloweds;
    public EnabledArea(){

//        this.productList = new ArrayList<>();
    }
}
