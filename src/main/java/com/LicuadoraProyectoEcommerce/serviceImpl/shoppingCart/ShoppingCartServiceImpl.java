package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.ShoppingCartMapper;
import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Item;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ShoppingCartRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ItemService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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
    public ShoppingCartDto createEntity(ShoppingCartForm shoppingCartDto, HttpServletResponse response) throws IOException {
        ShoppingCart entity = shoppingCartMapper.createEntityFromForm(shoppingCartDto);
//        createBuyerCookies(response, entity);
        return shoppingCartMapper.getDtoFromEntity(shoppingCartRepository.save(entity));
    }

    @Override
    public ShoppingCartDto updateEntityById(Long id, ShoppingCartForm shoppingCartDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ShoppingCart shoppingCart = findEntityById(id);
//        validBuyerCookies(request, shoppingCart);
        ShoppingCart entity = shoppingCartMapper.updateEntityFromDto(shoppingCart, shoppingCartDto);
//        createBuyerCookies(response, entity);
        return shoppingCartMapper.getDtoFromEntity(shoppingCartRepository.save(entity));
    }
//    public void validBuyerCookies(HttpServletRequest request, ShoppingCart shoppingCart){
//            String buyerDni = Arrays.stream(request.getCookies())
//                .filter(cookie -> ("email_"+shoppingCart.getBuyerEmail()).equals(cookie.getName()))
//                .map(Cookie::getValue)
//                .findAny().get();
//            if(buyerDni!= shoppingCart.getBuyerDni())throw new BadRequestException("you don't have authorizations");
//    }
//    void createBuyerCookies(HttpServletResponse response, ShoppingCart shoppingCart) throws IOException {
//        Cookie buyerCookie= new Cookie("email_"+shoppingCart.getBuyerEmail(), "email_"+shoppingCart.getBuyerEmail());
//        buyerCookie.setMaxAge(60*60);
//        response.addCookie(buyerCookie);
//        response.getOutputStream();
//    }

//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name= "uuid", strategy = "uuid2")
//    @Column(name = "id", nullable = false)
//    private String id;
    @Override
    public ShoppingCartCompleteDto findById(Long id) {
        return shoppingCartMapper.getCompleteDtoFromEntity(findEntityById(id));
    }
    @Override
    public ShoppingCart findEntityById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id) + " (shopping cart)")));
        if(!shoppingCart.getShowEntity()) throw new BadRequestException("this shopping cart already was payment"); //TODO FILTRO PARA NO ACCEDER AL CARRITO QUE SE PAGO
        return shoppingCart;
    }
    @Override
    public List<ShoppingCartDto> geDtoListPagination(Integer page) {
        List<ShoppingCart> listEntities = shoppingCartRepository.findAllByShowEntity(true, PageRequest.of(page, SIZE_TEN)); //TODO FILTRO PARA NO ACCEDER AL CARRITO QUE SE PAGO
        return shoppingCartMapper.getListDtoFromListEntity(listEntities);
    }
    @Override
    public Map<String, String> deleteById(Long id, HttpServletResponse response) {
        ShoppingCart shoppingCart = findEntityById(id);
        Cookie userNameCookieRemove = new Cookie("user_"+shoppingCart.getBuyerName(), "");
        userNameCookieRemove.setMaxAge(0);
        response.addCookie(userNameCookieRemove);
        shoppingCartRepository.delete(shoppingCart);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }
    public ShoppingCartCompleteDto addProductToCart(Long id, Long idProduct, Integer amountProduct) {
        SellerProduct product  = sellerProductService.findEntityById(idProduct);
        ShoppingCart shoppingCart = findEntityById(id);
        isTheSameStore(product, shoppingCart);
        shoppingCart.getItems().add(itemService.createNewOrderProduct(shoppingCart, product, (amountProduct==0)?1:amountProduct));
        return shoppingCartMapper.getCompleteDtoFromEntity(shoppingCart);
    }
    public ShoppingCartCompleteDto updateProductToCart(Long id, Long idItem, Long idProduct, Integer amountProduct) {
        SellerProduct product  = (idProduct==null) ? null:sellerProductService.findEntityById(idProduct);
        ShoppingCart shoppingCart = findEntityById(id);
        isTheSameStore(product, shoppingCart);
        Item item = itemService.updateEntityById(idItem, product, (amountProduct==0)?1:amountProduct);
        return shoppingCartMapper.getCompleteDtoFromEntity(item.getShoppingCart());
    }
    void isTheSameStore(SellerProduct product, ShoppingCart shoppingCart){
        List<Item> items = shoppingCart.getItems();
        // produtos publicados, producto del mismo vendedor, no se puede agregar el mismo producto
        if(product!=null){
            if(product.getPublication()==null || product.getPublication().getPublicationSate()!= PublicationSate.PUBLISHED)throw new BadRequestException("you cant add to the cart products that are not published");
            if(!items.isEmpty()) {
                SellerProduct sellerProduct = items.stream().map(p -> p.getSellerProduct()).findFirst().get();
                if (sellerProduct.equals(product) || sellerProduct.getSeller() != product.getSeller())
                    throw new BadRequestException("you cant add or update the cart with the same products or products from another store");
                }
        }
    }
}
