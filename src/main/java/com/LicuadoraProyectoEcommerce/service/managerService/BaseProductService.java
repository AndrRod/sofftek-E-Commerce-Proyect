package com.LicuadoraProyectoEcommerce.service.managerService;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;

import java.util.List;
import java.util.Map;

public interface BaseProductService {
    BaseProductDtoComplete createBaseProduct(BaseProductDto baseProductDto);
    BaseProductDtoComplete getBaseProductById(Long id);
    List<BaseProductDtoComplete> getBaseProductListPage(Integer page);
    Map<String, String> deleteBaseProductById(Long id);
    BaseProductDtoComplete updateBaseProduct(Long id, BaseProductDto baseProductDto);

    BaseProduct findEntityById(Long id);
    BaseProductDtoComplete addEnabledAreaToEntity(Long id, EnabledArea enabledArea);
    BaseProductDtoComplete removeEnabledAreaToEntity(Long id, EnabledArea enabledArea);
}
