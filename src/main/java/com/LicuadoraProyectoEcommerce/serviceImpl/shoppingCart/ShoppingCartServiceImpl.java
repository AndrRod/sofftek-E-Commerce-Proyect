package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.productOrderForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.ShoppingCartMapper;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ProductOrder;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ShoppingCartRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ProductOrderService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private ProductOrderService productOrderService;
    @Override
    public ShoppingCartDto createEntity(ShoppingCartDto shoppingCartDto) {
        ShoppingCart entity = shoppingCartMapper.getEntityFromDto(shoppingCartDto);
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
        return shoppingCartRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id) + " (shopping cart)")));
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


    @Override //TODO ver si no es mejorar mandar un mensaje
    public ShoppingCartCompleteDto addProductToCart(Long id, Long idProduct, Integer amountProduct) {
        SellerProduct product  = sellerProductService.findEntityById(idProduct);
        ShoppingCart shoppingCart = findEntityById(id);
        isTheSameStore(product, shoppingCart);
        ProductOrder productOrder = productOrderService.createNewOrderProduct(shoppingCart, product, amountProduct);
        return shoppingCartMapper.getCompleteDtoFromEntity(productOrder.getShoppingCart());
    }
    @Override //TODO ver si no es mejorar mandar un mensaje
    public ShoppingCartCompleteDto updateProductToCart(Long id, Long idOrderProduct, Long idProduct, productOrderForm form) {
        SellerProduct product  = (idProduct==null) ? null:sellerProductService.findEntityById(idProduct);
        ShoppingCart shoppingCart = findEntityById(id);
        isTheSameStore(product, shoppingCart);
        ProductOrder productOrder = productOrderService.updateEntityById(idOrderProduct, product, form.getQuantityOfProducts());
        return shoppingCartMapper.getCompleteDtoFromEntity(productOrder.getShoppingCart());
    }
    void isTheSameStore(SellerProduct product, ShoppingCart shoppingCart){
        List<ProductOrder> productOrderList = shoppingCart.getProductOrders();
        Boolean isTrue = product==null || productOrderList.isEmpty() || productOrderList.stream().anyMatch(p->
                        p.getSellerProduct().getSeller().getStore().getSellerProducts().contains(product));
        if(!isTrue) throw new BadRequestException("you cant add to the cart products from another store or products that do not have a store");
    }
}
