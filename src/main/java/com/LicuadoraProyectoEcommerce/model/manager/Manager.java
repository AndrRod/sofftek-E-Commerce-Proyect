package com.LicuadoraProyectoEcommerce.model.manager;

import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Entity
@AllArgsConstructor @Builder
public class Manager{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @OneToMany(mappedBy = "managerCreator", cascade = CascadeType.ALL)
    private List<BaseProduct> baseProducts;
    public Manager(User user){
        this.user=user;
    }
    public Manager(){
        this.baseProducts = new ArrayList<>();
    }

    @PreRemove //TODO VER COMPORTAMIENTO no debe poder borrarse el producto si un vendedor lo esta usando
    public void deleteIfNotUsedBySeller(){
        if(!baseProducts.stream().anyMatch(p-> p.getSellerProducts().isEmpty()))
            throw new BadRequestException("this user cant cant be delete or update because there is almost a producto already is used by a Seller");
    }
    @PreUpdate //TODO no debe poder actualizarse si un comprador lo agrego a un carrito
    public void updateIfNotWasAddToAShoppingCart(){
        baseProducts.stream().map(p-> p.getSellerProducts()).forEach(sellerProducts -> {
            if(sellerProducts.stream().anyMatch(sellerProduct -> !sellerProduct.getItems().isEmpty())) throw new BadRequestException("this user cant be update because a buyer added a product to a shopping cart");
        });
    }
}
