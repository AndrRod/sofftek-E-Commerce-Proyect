package com.LicuadoraProyectoEcommerce.service.shoppingCart;

import com.LicuadoraProyectoEcommerce.form.OrderProductForm;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.OrderProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import io.vavr.collection.Tree;

import java.util.Map;

public interface OrderProductService {
    void createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct);
    OrderProduct findEntityById(Long id);
    Map<String, String> deleteById(Long id);
    void updateEntityById(Long id, SellerProduct sellerProduct, Integer amountProducts);
}
