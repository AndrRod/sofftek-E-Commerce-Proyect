package com.LicuadoraProyectoEcommerce.service;

import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;

import java.util.List;
import java.util.Map;

public interface BaseProductService {
    BaseProductDtoComplete createBaseProduct(BaseProductDto baseProductDto);
    BaseProductDtoComplete getBaseProductById(Long id);
    List<BaseProductDtoComplete> getBaseProductListPage(Integer page);
    Map<String, String> deleteBaseProductById(Long id);
    BaseProductDtoComplete updateBaseProduct(Long id, BaseProductDto baseProductDto);
}
