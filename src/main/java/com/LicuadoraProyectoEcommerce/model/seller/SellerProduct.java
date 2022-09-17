package com.LicuadoraProyectoEcommerce.model.seller;


import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class SellerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double finalPrice;
    @ManyToOne
    @JoinColumn(name = "base_product_id", referencedColumnName = "id")
    private BaseProduct baseProduct;
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sellerProduct_area",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<SellerArea> areas;
}
