package com.LicuadoraProyectoEcommerce.mapper.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ProductDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.OrderProduct;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ShoppingCartMapper {

    public ShoppingCart getEntityFromDto(ShoppingCartDto shoppingCartDto){
        return new ShoppingCart(shoppingCartDto.getBuyerName(), shoppingCartDto.getBuyerEmail(), shoppingCartDto.getBuyerDni(), PaymentMethod.valueOf(shoppingCartDto.getPaymentMethod()));
    }
    public ShoppingCartDto getDtoFromEntity(ShoppingCart shoppingCart){
        return new ShoppingCartDto(shoppingCart.getId(), shoppingCart.getBuyerName(), shoppingCart.getBuyerEmail(), shoppingCart.getBuyerDni(), shoppingCart.getFinalPrice(), shoppingCart.getPaymentMethod().toString());
    }

    public List<ShoppingCartDto> getListDtoFromListEntity(List<ShoppingCart> shoppingCartList){
        return shoppingCartList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
    public ShoppingCart updateEntityFromDto(ShoppingCart shoppingCart, ShoppingCartDto shoppingCartDto){
        Stream.of(shoppingCartDto).forEach((dto)->{
            if(dto.getBuyerName() != null) shoppingCart.setBuyerName(dto.getBuyerName());
            if(dto.getBuyerDni() != null) shoppingCart.setBuyerDni(dto.getBuyerDni());
            if(dto.getBuyerEmail() != null) shoppingCart.setBuyerEmail(dto.getBuyerEmail());
        });
        return shoppingCart;
    }

    public ShoppingCartCompleteDto getCompleteDtoFromEntity(ShoppingCart shoppingCart){
        List<OrderProduct> listEntity = shoppingCart.getOrderProducts();
        return new ShoppingCartCompleteDto(shoppingCart.getId(), shoppingCart.getBuyerName(), shoppingCart.getBuyerEmail(), shoppingCart.getBuyerDni(), shoppingCart.getFinalPrice(),
                getListProductDtoFromSellerProductEntity(listEntity.stream().map(o-> o.getSellerProduct()).collect(Collectors.toList())));
    }
    public ProductDto getProductDtoFromSellerProductEntity(SellerProduct sellerProduct){
        return new ProductDto(sellerProduct.getBaseProduct().getName(), sellerProduct.getFinalPrice());
    }
    public List<ProductDto> getListProductDtoFromSellerProductEntity(List<SellerProduct> sellerProducts){
        return sellerProducts.stream().map(this::getProductDtoFromSellerProductEntity).collect(Collectors.toList());
    }
}
