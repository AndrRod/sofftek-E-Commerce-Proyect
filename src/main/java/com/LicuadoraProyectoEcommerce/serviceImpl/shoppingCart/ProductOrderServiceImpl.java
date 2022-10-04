package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Items;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ProductOrderRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public Items createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct) {
        Items items = new Items(shoppingCart, product, amountProduct);
        productOrderRepository.save(items);
        return items;
    }

    @Override
    public Items findEntityById(Long id) {
        return productOrderRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        productOrderRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public Items updateEntityById(Long id, SellerProduct product, Integer amountProduct) {
        Items items = findEntityById(id);
        if(amountProduct!=null) items.setQuantityOfProducts(amountProduct);
        if(product!=null) items.setSellerProduct(product);
        productOrderRepository.save(items);
        return items;
    }
}
