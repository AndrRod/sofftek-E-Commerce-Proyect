package com.LicuadoraProyectoEcommerce.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity @AllArgsConstructor
public class Seller extends User{
    public Seller(Long id, String name, String email, String password) {
        super(id, name, email, password, Role.SELLER);
    }
}
