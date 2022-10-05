package com.LicuadoraProyectoEcommerce.mapper.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.model.seller.Publication;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StoreMapper {
    @Autowired
    private SellerProductMapper sellerProductMapper;
    @Autowired
    private PublicationMapper publicationMapper;
    public Store createEntityFromDto(SellerStoreDto sellerStoreDto){
         return new Store(sellerStoreDto.getName(), sellerStoreDto.getDescription());
     }
    public SellerStoreCompleteDto getCompleteDtoFromEntity(Store store, List<PublicationDto> publicationsDto){
         return new SellerStoreCompleteDto(store.getId(), store.getName(), store.getDescription(), store.getPaymentMethods(),
                 (publicationsDto!=null)? publicationsDto :null);
    }
     public SellerStoreDto getDtoFromEntity(Store store){
        return new SellerStoreDto(store.getId(), store.getName(), store.getDescription());
     }
    public List<SellerStoreDto> getListDtoFromEntityList(List<Store> stores){
         return stores.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
     }
     public Store updateEntityFromDto(Store store, SellerStoreDto sellerStoreDto){
         Stream.of(sellerStoreDto).forEach(dto->{
             if(dto.getName()!= null) store.setName(dto.getName());
             if(dto.getDescription() != null) store.setDescription(dto.getDescription());
         });
         return store;
     }

}
