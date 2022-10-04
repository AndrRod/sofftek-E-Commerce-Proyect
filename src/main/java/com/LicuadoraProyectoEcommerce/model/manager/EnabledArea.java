package com.LicuadoraProyectoEcommerce.model.manager;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "enabledArea_customizationAllowed",
            joinColumns = @JoinColumn(name = "enableArea_id"),
            inverseJoinColumns = @JoinColumn(name = "customizationAllowed_id"))
    private List<CustomizationAllowed> customizationsAllowed;


    @ManyToMany(mappedBy = "areas")
    private List<SellerProduct> sellerProducts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "area_customization",
    joinColumns = @JoinColumn(name = "area_id"),
    inverseJoinColumns = @JoinColumn(name = "customization_id"))
    private List<SellerCustomization> customizations;

    public EnabledArea(String name){
        this.name = name;
    }
    public EnabledArea(){
        this.baseProducts = new ArrayList<>();
        this.customizationsAllowed = new ArrayList<>();
    }
    public void addCustomizationAllowedToEnabledArea(CustomizationAllowed customizationAllowed) {
        customizationsAllowed.add(customizationAllowed);
        customizationAllowed.getEnabledAreas().add(this);
    }
    public void removeCustomizationAllowedToEnabledArea(CustomizationAllowed customizationAllowed) {
        customizationsAllowed.remove(customizationAllowed);
        customizationAllowed.getEnabledAreas().remove(this);
    }
    public void removeProduct(BaseProduct product){
        this.getBaseProducts().remove(product);
        product.getEnabledAreas().remove(this);
    }
}
