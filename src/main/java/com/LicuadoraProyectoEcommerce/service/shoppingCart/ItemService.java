package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Item;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;

import java.util.Map;

public interface ItemService {
    Item createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct);
    Item findEntityById(Long id);
    Map<String, String> deleteById(Long id);
    Item updateEntityById(Long id, SellerProduct sellerProduct, Integer amountProducts);
}
