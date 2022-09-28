package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.OrderProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.OrderProductRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public void createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct) {
        OrderProduct orderProduct = new OrderProduct(shoppingCart, product, amountProduct);
        orderProductRepository.save(orderProduct);
    }

    @Override
    public OrderProduct findEntityById(Long id) {
        return orderProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        orderProductRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public void updateEntityById(Long id, SellerProduct product, Integer amountProduct) {
        OrderProduct orderProduct = findEntityById(id);
        if(amountProduct!=null) orderProduct.setQuantityOfProducts(amountProduct);
        if(product!=null) orderProduct.setSellerProduct(product);
        orderProductRepository.save(orderProduct);
    }
}
