package com.LicuadoraProyectoEcommerce.service.shoppingCart;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import io.vavr.control.Try;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PurchaseService {
    PurchaseDto createEntity(Long idShoppingCart, PaymentForm paymentForm);
    PurchaseDto updateEntity(Long id, Long idShoppingCart, PaymentForm paymentForm);
    Map<String, String> deleteById(Long id);
    PurchaseDto findById(Long id);
    Purchase findEntityById(Long id);
    List<PurchaseDto> geDtoListPagination(Integer page);
    Map<String, String> updateStatePurchase(Long id, String purchaseState, HttpServletRequest request);

}
