package com.LicuadoraProyectoEcommerce.repository.manager;

import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {
    List<BaseProduct> findByEnabledAreas(EnabledArea enabledArea);
}
