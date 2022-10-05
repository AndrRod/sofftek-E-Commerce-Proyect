package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.form.productOrderForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.ShoppingCartMapper;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Item;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ShoppingCartRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ItemService;
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
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private ItemService itemService;
    public ShoppingCartCompleteDto createEntity(ShoppingCartForm shoppingCartDto) {
        ShoppingCart entity = shoppingCartMapper.createEntityFromForm(shoppingCartDto);
//        shoppingCartExist(entity);
        return shoppingCartMapper.getCompleteDtoFromEntity(shoppingCartRepository.save(entity), false);
    }
    void shoppingCartExist(ShoppingCart shoppingCart){
        if(shoppingCartRepository.existsByBuyerEmailOrBuyerDni(shoppingCart.getBuyerEmail(), shoppingCart.getBuyerDni())) throw new BadRequestException("Already exists a shopping cart with this buyer email or dni");
    }

    @Override
    public ShoppingCartCompleteDto updateEntityById(Long id, ShoppingCartForm shoppingCartDto) {
        ShoppingCart entity = shoppingCartMapper.updateEntityFromDto(findEntityById(id), shoppingCartDto);
        return shoppingCartMapper.getCompleteDtoFromEntity(shoppingCartRepository.save(entity), false);
    }
    @Override
    public ShoppingCartCompleteDto findById(Long id) {
        return shoppingCartMapper.getCompleteDtoFromEntity(findEntityById(id), true);
    }
    @Override
    public ShoppingCart findEntityById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id) + " (shopping cart)")));
    }
    @Override
    public List<ShoppingCartCompleteDto> geDtoListPagination(Integer page) {
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
        Item item = itemService.createNewOrderProduct(shoppingCart, product, (amountProduct==0)?1:amountProduct);
        return shoppingCartMapper.getCompleteDtoFromEntity(item.getShoppingCart(), false);
    }
    @Override //TODO ver si no es mejorar mandar un mensaje
    public ShoppingCartCompleteDto updateProductToCart(Long id, Long idOrderProduct, Long idProduct, productOrderForm form) {
        SellerProduct product  = (idProduct==null) ? null:sellerProductService.findEntityById(idProduct);
        ShoppingCart shoppingCart = findEntityById(id);
        isTheSameStore(product, shoppingCart);
        Item item = itemService.updateEntityById(idOrderProduct, product, form.getQuantityOfProducts());
        return shoppingCartMapper.getCompleteDtoFromEntity(item.getShoppingCart(), false);
    }
    void isTheSameStore(SellerProduct product, ShoppingCart shoppingCart){
        List<Item> itemList = shoppingCart.getItems();
        Boolean isTrue = product==null || itemList.isEmpty() || itemList.stream().anyMatch(p->
                        p.getSellerProduct().getSeller().getStore().getPublication().contains(product));
        if(!isTrue) throw new BadRequestException("you cant add to the cart products from another store or products that do not have a store");
    }
}
