package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ProductOrder;
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
    public ProductOrder createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct) {
        ProductOrder productOrder = new ProductOrder(shoppingCart, product, amountProduct);
        productOrderRepository.save(productOrder);
        return productOrder;
    }

    @Override
    public ProductOrder findEntityById(Long id) {
        return productOrderRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        productOrderRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public ProductOrder updateEntityById(Long id, SellerProduct product, Integer amountProduct) {
        ProductOrder productOrder = findEntityById(id);
        if(amountProduct!=null) productOrder.setQuantityOfProducts(amountProduct);
        if(product!=null) productOrder.setSellerProduct(product);
        productOrderRepository.save(productOrder);
        return productOrder;
    }
}
