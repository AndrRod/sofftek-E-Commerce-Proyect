package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL, mappedBy = "manager")
    private List<BaseProduct> baseProducts;
    public Manager(User user){
        this.user=user;
    }
    public Manager(){
        this.baseProducts = new ArrayList<>();
    }
}
