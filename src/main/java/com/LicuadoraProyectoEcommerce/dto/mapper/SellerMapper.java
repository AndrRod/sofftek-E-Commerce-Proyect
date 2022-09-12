package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.dto.SellerCompleteDto;
import com.LicuadoraProyectoEcommerce.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SellerMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public SellerDto getDtoFromEntity(Seller seller){return new SellerDto(seller.getName(), seller.getEmail());}
    public List<SellerDto> getListDtoFromListEntity(List<Seller> sellerList){return sellerList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());}

    public Seller getEntityCreateFromDto(SellerCompleteDto sellerCompleteDto){
        return new Seller(null, sellerCompleteDto.getName(), sellerCompleteDto.getEmail(), passwordEncoder.encode(sellerCompleteDto.getPassword()));
    }
    public Seller getEntityUpdateFromDto(Seller seller, SellerCompleteDto sellerDto){
        Stream.of(seller).forEach((s)-> {
            if (sellerDto.getEmail() != null) s.setEmail(sellerDto.getEmail());
            if (sellerDto.getName() != null) s.setName(sellerDto.getName());
            if (sellerDto.getPassword() != null) s.setPassword(passwordEncoder.encode(sellerDto.getPassword()));
        });
        return seller;
    }
}
