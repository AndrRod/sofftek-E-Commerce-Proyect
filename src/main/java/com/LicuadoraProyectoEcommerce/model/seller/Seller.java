package com.LicuadoraProyectoEcommerce.model.seller;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Store store;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<SellerProduct> sellerProducts;

    public Seller(User user) {
        this.user=user;
    }
}
