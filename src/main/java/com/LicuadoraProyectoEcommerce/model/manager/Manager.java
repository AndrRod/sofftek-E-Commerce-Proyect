package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.model.User;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity
@AllArgsConstructor
public class Manager{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<BaseProduct> baseProducts;
    public Manager(){
        this.baseProducts = new ArrayList<>();
    }
}
