package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.PurchaseMapper;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.PurchaseRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PurchaseService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Override
    public PurchaseDto createEntity(Long idShoppingCart, PaymentForm paymentForm) {
        ShoppingCart shoppingCart = shoppingCartService.findEntityById(idShoppingCart);
        if(purchaseRepository.existsByShoppingCart(shoppingCart)) throw new BadRequestException("the shopping cart is already paid");
//        paymentMethodExist(paymentForm, shoppingCart); //TODO VERIFICAR QUE EL METODO DE PAGO EXISTA
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
//        paymentMethodExist(paymentForm, purchase.getShoppingCart()); //TODO VERIFICAR QUE EL METODO DE PAGO EXISTA
        Purchase updateEntity = purchaseMapper.updateFromForm(purchase, paymentForm);
        return purchaseMapper.getDtoFromEntity(purchaseRepository.save(updateEntity));
    }
//    void paymentMethodExist(PaymentForm paymentForm, ShoppingCart shoppingCart){
//        Store store = shoppingCart.getItems().get(0).getSellerProduct().getStore();
//        if (!store.getPaymentMethods().stream().anyMatch(m-> m.toString().equals(paymentForm.getPaymentMethod()))){
//            String payMethodsAllowedString = store.getPaymentMethods().stream().map(m-> m.toString()).collect(Collectors.joining(", "));
//            throw new BadRequestException("payment " + paymentForm.getPaymentMethod() + " method not allowed for the store or not exist. The allowed methods are: " + payMethodsAllowedString);}
//    }
    @Override
    public Map<String, String> deleteById(Long id) {
        purchaseRepository.delete(findEntityById(id));
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
}
