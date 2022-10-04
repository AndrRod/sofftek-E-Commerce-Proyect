package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Items;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;

import java.util.Map;

public interface ProductOrderService {
    Items createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct);
    Items findEntityById(Long id);
    Map<String, String> deleteById(Long id);
    Items updateEntityById(Long id, SellerProduct sellerProduct, Integer amountProducts);
}
