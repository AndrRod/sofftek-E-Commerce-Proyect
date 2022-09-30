package com.LicuadoraProyectoEcommerce.serviceImpl.shoppingCart;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PaymentDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.mapper.shoppingCart.PaymentMapper;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.PaymentRepository;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.PaymentService;
import com.LicuadoraProyectoEcommerce.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public PaymentDto createEntity(Long idShoppingCart, PaymentForm paymentForm) {
        ShoppingCart shoppingCart = shoppingCartService.findEntityById(idShoppingCart);
        paymentMethodExist(paymentForm, shoppingCart);
        Payment payment = paymentMapper.createFromForm(shoppingCart, paymentForm);
        return paymentMapper.getDtoFromEntity(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto updateEntity(Long id, Long idShoppingCart, PaymentForm paymentForm) {
        Payment payment = findEntityById(id);
        if(idShoppingCart!=null){
        ShoppingCart shoppingCart = shoppingCartService.findEntityById(idShoppingCart);
        payment.setShoppingCart(shoppingCart);
        }
        paymentMethodExist(paymentForm, payment.getShoppingCart());
        Payment updateEntity = paymentMapper.updateFromForm(payment, paymentForm);
        return paymentMapper.getDtoFromEntity(paymentRepository.save(updateEntity));
    }
    void paymentMethodExist(PaymentForm paymentForm, ShoppingCart shoppingCart){
        if(PaymentMethod.valueOf(paymentForm.getPaymentMethod())== null ||
                !shoppingCart.getProductOrders().get(0).getSellerProduct().getStore().getPaymentMethods().contains(PaymentMethod.valueOf(paymentForm.getPaymentMethod()))) throw new BadRequestException("payment method not exist or not allowed for the store");
    }
    @Override
    public Map<String, String> deleteById(Long id) {
        paymentRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public PaymentDto findById(Long id) {
        return paymentMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Payment findEntityById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<PaymentDto> geDtoListPagination(Integer page) {
        List<Payment> listEntities = paymentRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return paymentMapper.getListDtoFromListEntity(listEntities);
    }
}
