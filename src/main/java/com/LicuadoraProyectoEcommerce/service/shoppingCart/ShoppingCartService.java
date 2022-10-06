package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
  ShoppingCartDto createEntity(ShoppingCartForm form, HttpServletResponse request) throws IOException;
  ShoppingCartDto updateEntityById(Long id, ShoppingCartForm form, HttpServletRequest request, HttpServletResponse response)throws IOException;
  ShoppingCartCompleteDto addProductToCart(Long id, Long idProduct, Integer amountProduct);
  ShoppingCartCompleteDto updateProductToCart(Long id, Long idItem, Long idProduct, Integer amountProduct);
  ShoppingCartCompleteDto findById(Long id);
  ShoppingCart findEntityById(Long id);
  List<ShoppingCartDto> geDtoListPagination(Integer page);
  Map<String, String> deleteById(Long id, HttpServletResponse response);
}
