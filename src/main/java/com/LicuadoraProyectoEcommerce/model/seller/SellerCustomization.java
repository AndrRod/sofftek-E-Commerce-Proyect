package com.LicuadoraProyectoEcommerce.model.seller;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class SellerCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "cant be empty or null")
    private String type;
    @NotBlank(message = "cant be empty or null")
    private String name;
    @ManyToMany(mappedBy = "customizations")
    private List<SellerArea> areas;
}
