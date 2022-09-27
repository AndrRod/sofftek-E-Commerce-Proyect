package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.ShoppingCartMapper;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ShoppingCartRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public ShoppingCartDto createEntity(ShoppingCartDto shoppingCartDto) {
        ShoppingCart entity = shoppingCartMapper.getEntityFromDto(shoppingCartDto);
        if(!entity.getOrderProducts().get(0).getSellerProduct().getStore().getPaymentMethods().contains(entity.getPaymentMethod())) throw new NotFoundException(messageHandler.message("payment.method.not.found", entity.getPaymentMethod().toString()));
        return shoppingCartMapper.getDtoFromEntity(shoppingCartRepository.save(entity));
    }

    @Override
    public ShoppingCartDto updateEntityById(Long id, ShoppingCartDto shoppingCartDto) {
        ShoppingCart entity = shoppingCartMapper.updateEntityFromDto(findEntityById(id), shoppingCartDto);
        return shoppingCartMapper.getDtoFromEntity(shoppingCartRepository.save(entity));
    }

    @Override
    public ShoppingCartCompleteDto findById(Long id) {
        return shoppingCartMapper.getCompleteDtoFromEntity(findEntityById(id));
    }

    @Override
    public ShoppingCart findEntityById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<ShoppingCartDto> geDtoListPagination(Integer page) {
        List<ShoppingCart> listEntities = shoppingCartRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return shoppingCartMapper.getListDtoFromListEntity(listEntities);
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        shoppingCartRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }
}
