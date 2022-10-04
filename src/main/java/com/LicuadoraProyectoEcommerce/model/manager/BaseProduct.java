package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor
public class BaseProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer daysToManufacture;
    @OneToMany(mappedBy = "baseProduct", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerProduct> sellerProducts;
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager managerCreator;

    @CreationTimestamp
    @Column(name = "creationDate", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate;

    private String managerLastUpdate;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.LAZY)
    @JoinTable(
            name = "baseProduct_enableArea",
            joinColumns = @JoinColumn(name = "base_product_id"),
            inverseJoinColumns = @JoinColumn(name = "enabled_area_id"))
    private List<EnabledArea> enabledAreas;
    public BaseProduct(){
        this.enabledAreas = new ArrayList<>();
    }
    public BaseProduct(String name, Double price, Integer daysToManufacture, List<EnabledArea> enabledAreas){
        this.name = name;
        this.price = price;
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
