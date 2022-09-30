package com.LicuadoraProyectoEcommerce.dto.shoppingCart;

import com.LicuadoraProyectoEcommerce.model.shoppingCart.Payment;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.StatusPayment;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Transient;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private String name;
    private String dni;
    private String numberOfTransaction;
    private Double finalPrice;
    private StatusPayment status;
    private String invoiceNumber;

    public InvoiceDto(Long id, StatusPayment status, String invoiceNumber, Payment payment){
        this.id = id;
        this.status=status;
        this.invoiceNumber = invoiceNumber;
        this.name= payment.getShoppingCart().getBuyerName();
        this.dni= payment.getShoppingCart().getBuyerDni();
        this.numberOfTransaction = payment.getNumberOfTransaction();
    }
}
