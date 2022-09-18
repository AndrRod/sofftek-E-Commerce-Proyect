package com.LicuadoraProyectoEcommerce.model.seller;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
public class SellerArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "areas")
    private List<SellerProduct> sellerProducts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "area_customization",
    joinColumns = @JoinColumn(name = "area_id"),
    inverseJoinColumns = @JoinColumn(name = "customization_id"))
    private List<SellerCustomization> customizations;
}