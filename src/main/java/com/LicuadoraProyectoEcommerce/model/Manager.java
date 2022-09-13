package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor
public class Manager{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    public Manager(){
        this.role = Role.MANAGER;
    }
}
