package com.LicuadoraProyectoEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @Entity
public class EnabledArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "this param cant be null o empty")
    private String name;
    @ManyToMany(mappedBy = "enabledAreas")
    private List<BaseProduct> baseProducts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "enabledArea_customizationAllowed",
            joinColumns = @JoinColumn(name = "enableArea_id"),
            inverseJoinColumns = @JoinColumn(name = "customizationAllowed_id"))
    private List<CustomizationAllowed> customizationsAllowed;
    public EnabledArea(){
        this.baseProducts = new ArrayList<>();
        this.customizationsAllowed = new ArrayList<>();
    }
    public void addCustomizationAllowedToEnabledArea(CustomizationAllowed customizationAllowed) {
        customizationsAllowed.add(customizationAllowed);
    }
    public void removeCustomizationAllowedToEnabledArea(CustomizationAllowed customizationAllowed) {
        customizationsAllowed.remove(customizationAllowed);
    }
}
