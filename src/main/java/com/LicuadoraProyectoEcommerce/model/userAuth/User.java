package com.LicuadoraProyectoEcommerce.model.userAuth;

import com.LicuadoraProyectoEcommerce.model.userAuth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data @Entity
@AllArgsConstructor
@NoArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "can't be null or empty")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "can't be null or empty")
    private String email;
    @NotBlank(message = "can't be null or empty")
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
