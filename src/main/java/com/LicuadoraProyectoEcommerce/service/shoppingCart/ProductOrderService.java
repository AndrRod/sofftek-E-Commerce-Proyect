package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ProductOrder;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;

import java.util.Map;

public interface ProductOrderService {
    ProductOrder createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct);
    ProductOrder findEntityById(Long id);
    Map<String, String> deleteById(Long id);
    ProductOrder updateEntityById(Long id, SellerProduct sellerProduct, Integer amountProducts);
}
