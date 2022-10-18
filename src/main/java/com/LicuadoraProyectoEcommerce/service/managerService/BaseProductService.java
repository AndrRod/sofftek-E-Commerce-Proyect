package com.LicuadoraProyectoEcommerce.service.managerService;

import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BaseProductService {
    BaseProductDtoComplete createBaseProduct(BaseProductDto baseProductDto, HttpServletRequest request);
    BaseProductDtoComplete getBaseProductById(Long id);
    List<BaseProductDtoComplete> getBaseProductListPage(Integer page);
    Map<String, String> deleteBaseProductById(Long id);
    BaseProductDtoComplete updateBaseProduct(Long id, BaseProductDto baseProductDto, HttpServletRequest request);

    BaseProduct findEntityById(Long id);
    BaseProductDtoComplete addEnabledAreaToEntity(Long id, Long idArea, HttpServletRequest request);
    BaseProductDtoComplete removeEnabledAreaToEntity(Long id,  Long idArea, HttpServletRequest request);
}
