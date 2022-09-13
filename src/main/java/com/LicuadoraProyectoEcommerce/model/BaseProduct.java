package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
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
}
