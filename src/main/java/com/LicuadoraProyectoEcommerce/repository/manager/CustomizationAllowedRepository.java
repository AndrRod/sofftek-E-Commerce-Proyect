package com.LicuadoraProyectoEcommerce.repository.manager;

import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomizationAllowedRepository extends JpaRepository<CustomizationAllowed, Long> {
    Optional<CustomizationAllowed> findByType(String type);

    boolean existsByType(String type);

    List<CustomizationAllowed> findByEnabledAreas(EnabledArea enabledArea);


}
