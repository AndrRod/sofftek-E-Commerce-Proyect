package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Item;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ItemRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public Item createNewOrderProduct(ShoppingCart shoppingCart, SellerProduct product, Integer amountProduct) {
        Item item = new Item(shoppingCart, product, amountProduct);
        itemRepository.save(item);
        return item;
    }

    @Override
    public Item findEntityById(Long id) {
        return itemRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        itemRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public Item updateEntityById(Long id, SellerProduct product, Integer amountProduct) {
        Item item = findEntityById(id);
        if(amountProduct!=null) item.setQuantityOfProducts(amountProduct);
        if(product!=null) item.setSellerProduct(product);
        itemRepository.save(item);
        return item;
    }
}
