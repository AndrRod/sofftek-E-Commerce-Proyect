package com.LicuadoraProyectoEcommerce.mapper.shoppingCart;

import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ProductDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ShoppingCartDto;
import com.LicuadoraProyectoEcommerce.form.ShoppingCartForm;
import com.LicuadoraProyectoEcommerce.model.shoppingCart.ShoppingCart;
import com.LicuadoraProyectoEcommerce.repository.shoppingCart.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ShoppingCartMapper {
    @Autowired
    private ItemRepository itemRepository;

    public ShoppingCart getEntityFromDto(ShoppingCartDto shoppingCartDto){
        return new ShoppingCart(shoppingCartDto.getBuyerName(), shoppingCartDto.getBuyerEmail(), shoppingCartDto.getBuyerDni());
    }
    public ShoppingCartDto getDtoFromEntity(ShoppingCart shoppingCart){
        return new ShoppingCartDto(shoppingCart.getId(), shoppingCart.getBuyerName(), shoppingCart.getBuyerEmail(), shoppingCart.getBuyerDni(), shoppingCart.getFinalPrice());}

    public List<ShoppingCartCompleteDto> getListDtoFromListEntity(List<ShoppingCart> shoppingCartList){
        return shoppingCartList.stream().map(c->getCompleteDtoFromEntity(c, false)).collect(Collectors.toList());
    }
    public ShoppingCart updateEntityFromDto(ShoppingCart shoppingCart, ShoppingCartForm shoppingCartDto){
        Stream.of(shoppingCartDto).forEach((dto)->{
            if(dto.getBuyerName() != null) shoppingCart.setBuyerName(dto.getBuyerName());
            if(dto.getBuyerDni() != null) shoppingCart.setBuyerDni(dto.getBuyerDni());
            if(dto.getBuyerEmail() != null) shoppingCart.setBuyerEmail(dto.getBuyerEmail());
        });
        return shoppingCart;
    }

    public ShoppingCartCompleteDto getCompleteDtoFromEntity(ShoppingCart shoppingCart, boolean showProducts){
        return new ShoppingCartCompleteDto(shoppingCart.getId(), shoppingCart.getBuyerName(), shoppingCart.getBuyerEmail(), shoppingCart.getBuyerDni(), shoppingCart.getFinalPrice()
                , (showProducts)?getProductDtoFromShoppingCart(shoppingCart): null);
    }
    public List<ProductDto> getProductDtoFromShoppingCart(ShoppingCart shoppingCart){
        List<ProductDto> productsDto = new ArrayList<>();
        shoppingCart.getItems().stream().forEach(o-> {
            productsDto.add(new ProductDto(o.getId(), o.getSellerProduct().getBaseProduct().getName(), o.getSellerProduct().getDescription(), o.getFinalPricePerQuantity(), o.getQuantityOfProducts()));});
        return productsDto;
    }

    public ShoppingCart createEntityFromForm(ShoppingCartForm shoppingCartDto) {
        return new ShoppingCart(shoppingCartDto.getBuyerName(), shoppingCartDto.getBuyerEmail(), shoppingCartDto.getBuyerDni());
    }
}
