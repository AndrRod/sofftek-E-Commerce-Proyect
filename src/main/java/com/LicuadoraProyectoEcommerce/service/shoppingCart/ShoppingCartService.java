package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.form.productOrderForm;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
  ShoppingCartCompleteDto createEntity(ShoppingCartForm form);
  ShoppingCartCompleteDto updateEntityById(Long id, ShoppingCartForm form);
  ShoppingCartCompleteDto addProductToCart(Long id, Long idProduct, Integer amountProduct);
  ShoppingCartCompleteDto updateProductToCart(Long id, Long idOrderProduct, Long idProduct, productOrderForm form);
  ShoppingCartCompleteDto findById(Long id);
  ShoppingCart findEntityById(Long id);
  List<ShoppingCartCompleteDto> geDtoListPagination(Integer page);
  Map<String, String> deleteById(Long id);
}
