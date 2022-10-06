package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.PurchaseMapper;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.PurchaseRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PurchaseService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private UserAuthService userAuthService;
    @Override
    public PurchaseDto createEntity(Long idShoppingCart, PaymentForm paymentForm) {
        ShoppingCart shoppingCart = shoppingCartService.findEntityById(idShoppingCart);
        if(purchaseRepository.existsByShoppingCart(shoppingCart)) throw new BadRequestException("the shopping cart is already paid");
        validShoppingCartConditions(paymentForm, shoppingCart);
        Purchase purchase = purchaseMapper.createFromForm(shoppingCart, paymentForm);
        return purchaseMapper.getDtoFromEntity(purchaseRepository.save(purchase));
    }

    @Override
    public PurchaseDto updateEntity(Long id, Long idShoppingCart, PaymentForm paymentForm) {
        Purchase purchase = findEntityById(id);
        if(idShoppingCart!=null && idShoppingCart != purchase.getShoppingCart().getId()){
        ShoppingCart shoppingCart = shoppingCartService.findEntityById(idShoppingCart);
        if(purchaseRepository.existsByShoppingCart(shoppingCart)) throw new BadRequestException("the shopping cart is already paid");
        purchase.setShoppingCart(shoppingCart);
        }
        validShoppingCartConditions(paymentForm, purchase.getShoppingCart());
        Purchase updateEntity = purchaseMapper.updateFromForm(purchase, paymentForm);
        return purchaseMapper.getDtoFromEntity(purchaseRepository.save(updateEntity));
    }
    void validShoppingCartConditions(PaymentForm paymentForm, ShoppingCart shoppingCart){
        if(shoppingCart.getItems().isEmpty()) throw new BadRequestException("before to pay you have to add products to shopping cart");
        Store store = shoppingCart.getItems().get(0).getSellerProduct().getPublication().getStore();
        if (!store.getPaymentMethods().stream().anyMatch(m-> m.toString().equals(paymentForm.getPaymentMethod()))){
            String payMethodsAllowedString = store.getPaymentMethods().stream().map(m-> m.toString()).collect(Collectors.joining(", "));
            throw new BadRequestException("payment " + paymentForm.getPaymentMethod() + " method not allowed for the store or not exist. The allowed methods are: " + payMethodsAllowedString);}
    }
    @Override
    public Map<String, String> deleteById(Long id) {
        Purchase purchase = findEntityById(id);
//        purchase.getShoppingCart().setShowEntity(true);
        purchaseRepository.delete(purchase);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public PurchaseDto findById(Long id) {
        return purchaseMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Purchase findEntityById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<PurchaseDto> geDtoListPagination(Integer page) {
        List<Purchase> listEntities = purchaseRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return purchaseMapper.getListDtoFromListEntity(listEntities);
    }
    @Override
    public Map<String, String> updateStatePurchase(Long id, String purchaseState, HttpServletRequest request) {
        StatusPayment statusPayment = Try.of(()-> StatusPayment.valueOf(purchaseState.toUpperCase(Locale.ROOT))).getOrElseThrow(()-> new NotFoundException("the "+ purchaseState + " status doesn't exists"));
        Purchase purchase = findEntityById(id);
        userAuthService.isSellerProductSellerCreator(request, purchase.getShoppingCart().getItems().get(0).getSellerProduct());
        purchase.setStatus(statusPayment);
        purchaseRepository.save(purchase);
        return Map.of("Message", "the purchase state was update to " + purchaseState);
    }
}
