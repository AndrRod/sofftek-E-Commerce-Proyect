package com.LicuadoraProyectoEcommerce.mapper.shoppingCart;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.PaymentDto;
import com.LicuadoraProyectoEcommerce.form.PaymentForm;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class PaymentMapper {
    public PaymentDto getDtoFromEntity(Payment payment){
        return new PaymentDto(payment.getId(), payment.getPaymentMethod(), payment.getNumberOfTransaction(), payment.getTotalPurchase(), payment.getProducts());
    }
    public List<PaymentDto> getListDtoFromListEntity(List<Payment> payments){
        return payments.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public Payment createFromForm(ShoppingCart shoppingCart, PaymentForm payment){
        return new Payment(shoppingCart, PaymentMethod.valueOf(payment.getPaymentMethod()), payment.getNumberOfTransaction());
    }
    public Payment updateFromForm(Payment payment, PaymentForm form){
        Stream.of(form).forEach(f->{
            if(f.getPaymentMethod() != null) payment.setPaymentMethod(PaymentMethod.valueOf(f.getPaymentMethod()));
            if(f.getNumberOfTransaction() != null) payment.setNumberOfTransaction(f.getNumberOfTransaction());
        });
        return payment;
    }
}
