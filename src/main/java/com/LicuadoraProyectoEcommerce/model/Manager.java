package com.LicuadoraProyectoEcommerce.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Manager extends User{
    public Manager() {
        super();
         this.setRole(Role.SELLER);
    }
}
