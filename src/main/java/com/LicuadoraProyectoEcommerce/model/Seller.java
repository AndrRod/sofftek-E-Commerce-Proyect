package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Seller extends User{
    public Seller() {
        super();
        this.setRole(Role.USER);
    }
}
