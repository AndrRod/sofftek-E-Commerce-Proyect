package com.LicuadoraProyectoEcommerce.mapper.shoppingCart;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PurchaseDto;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Purchase;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class PurchaseMapper {
    public PurchaseDto getDtoFromEntity(Purchase purchase){
        return new PurchaseDto(purchase.getId(), purchase.getStoreName(), purchase.getPaymentMethod(), purchase.getNumberOfTransaction(), purchase.getTotalPurchase(), purchase.getProducts(), purchase.getBuyerName(), purchase.getBuyerEmail(), purchase.getBuyerDni());
    }
    public List<PurchaseDto> getListDtoFromListEntity(List<Purchase> purchases){
        return purchases.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public Purchase createFromForm(ShoppingCart shoppingCart, PaymentForm payment){
        return new Purchase(shoppingCart, PaymentMethod.valueOf(payment.getPaymentMethod()), payment.getNumberOfTransaction());
    }
    public Purchase updateFromForm(Purchase purchase, PaymentForm form){
        Stream.of(form).forEach(f->{
            if(f.getPaymentMethod() != null) purchase.setPaymentMethod(PaymentMethod.valueOf(f.getPaymentMethod()));
            if(f.getNumberOfTransaction() != null) purchase.setNumberOfTransaction(f.getNumberOfTransaction());
        });
        return purchase;
    }
}
