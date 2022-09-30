package com.LicuadoraProyectoEcommerce.service.shoppingCart;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PaymentDto;
import com.LicuadoraProyectoEcommerce.form.InvoiceForm;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    PaymentDto createEntity(Long idShoppingCart, PaymentForm paymentForm);
    PaymentDto updateEntity(Long id, Long idShoppingCart, PaymentForm paymentForm);
    Map<String, String> deleteById(Long id);
    PaymentDto findById(Long id);
    Payment findEntityById(Long id);
    List<PaymentDto> geDtoListPagination(Integer page);
}
