package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
  ShoppingCartDto createEntity(ShoppingCartDto shoppingCartDto);
  ShoppingCartDto updateEntityById(Long id, ShoppingCartDto shoppingCartDto);
  ShoppingCartCompleteDto findById(Long id);
  ShoppingCart findEntityById(Long id);
  List<ShoppingCartDto> geDtoListPagination(Integer page);
  Map<String, String> deleteById(Long id);
}
