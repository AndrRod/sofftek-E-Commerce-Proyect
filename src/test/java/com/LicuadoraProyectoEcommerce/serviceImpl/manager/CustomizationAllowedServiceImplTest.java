package com.LicuadoraProyectoEcommerce.serviceImpl.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.manager.CustomizationAllowedMapper;
import com.LicuadoraProyectoEcommerce.mapper.manager.ManagerMapper;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.repository.manager.CustomizationAllowedRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

class CustomizationAllowedServiceImplTest { //TODO FALTA COMPLETAR TEST
    @Mock
    private CustomizationAllowedRepository repository;
    @InjectMocks
    private CustomizationAllowedServiceImpl underTest;
    @Mock
    private CustomizationAllowedMapper mp;
    @Mock
    private MessageHandler ms;

    SellerProduct sellerProductTest = SellerProduct.builder().build();
    EnabledArea areaTest = EnabledArea.builder().sellerProducts(List.of(sellerProductTest)).build();
    EnabledArea areaLackSellerProduct;
//            EnabledArea.builder().sellerProducts(new ArrayList<>()).customizationsAllowed(List.of(custTestAviodIllegalRef)).build();
    CustomizationAllowed custTest = CustomizationAllowed.builder().id(1L).type("color").enabledAreas(List.of(areaTest)).build();
    CustomizationAllowed custTestLackSellerProduct = CustomizationAllowed.builder().id(1L).type("color").enabledAreas(List.of(areaLackSellerProduct)).build();
    CustomizationAllowedDto custDtoTest =  CustomizationAllowedDto.builder().id(1L).type("color").build();
    CustomizationAllowedCompleteDto custCompleteDtoTest = CustomizationAllowedCompleteDto.builder().id(1L).type("color").build();

    @Test
    void findEntityById() {
        when(repository.findById(1L)).thenReturn(Optional.of(custTest));
        assert underTest.findEntityById(1L).equals(custTest);
    }
    @Test
    void notFoundEntityById(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        when(ms.message("not.found", String.valueOf(1L))).thenReturn("message");
        assertThrows(NotFoundException.class, () -> Optional
                .ofNullable(underTest.findEntityById(1L)));
    }
    @Test
    void createEntity() {
        when(mp.createEntityFromDto(custDtoTest)).thenReturn(custTest);
        when(mp.getDtoFromEntity(custTest)).thenReturn(custDtoTest);
        when(repository.save(any())).thenReturn(custTest);
        CustomizationAllowedDto custService= underTest.createEntity(custDtoTest);
        assertThat(custService).usingRecursiveComparison().isEqualTo(custDtoTest);
    }

    @Test
    void findDtoById() {
        when(mp.getDtoFromEntity(custTest)).thenReturn(custDtoTest);
        when(repository.findById(1L)).thenReturn(Optional.of(custTest));
        assert underTest.findDtoById(1L).equals(custDtoTest);
    }

    @Test
    void deleteEntityById() {
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(repository.findById(anyLong())).thenReturn(Optional.of(custTestLackSellerProduct));
        doNothing().when(repository).delete(any());
        assertThat(underTest.deleteEntityById(1L)).isEqualTo(new HashMap<String, String>(){{put("Message", "message");}});
    }
    @Test
    void deleteExceptIsUsingBySellerProductEntityById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(custTest));
        when(ms.message("cant.delete", null)).thenReturn("message");
        assertThrows(BadRequestException.class, () ->  Optional
                .ofNullable(underTest.deleteEntityById(1L)));
    }

    @Test
    void findDtoListPagination() {
        Page<CustomizationAllowed> pageList = new PageImpl(Arrays.asList(custTest));
        when(mp.getListDtoFromListEntity(Arrays.asList(custTest))).thenReturn(Arrays.asList(custDtoTest));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(pageList);
        List<CustomizationAllowedDto> listService = underTest.findDtoListPagination(0);
        assertThat(listService.size()).isEqualTo(pageList.getSize());
    }

    @Test
    void updateEntity() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(custTest));
        when(mp.updateEntityFromDto(custTest, custDtoTest)).thenReturn(custTest);
        when(repository.save(custTest)).thenReturn(custTest);
        when(mp.getDtoFromEntity(custTest)).thenReturn(custDtoTest);
        CustomizationAllowedDto productService = underTest.updateEntity(1L, custDtoTest);
        assertThat(productService).usingRecursiveComparison().isEqualTo(custTest);
    }

    @Test
    void findByType() {
        when(repository.findByType("color")).thenReturn(Optional.of(custTest));
        assert underTest.findByType("color").equals(custTest);
    }
    @Test
    void notFoundByType(){
        when(repository.findByType("color")).thenReturn(Optional.empty());
        when(ms.message("not.found.name", "color")).thenReturn("message");
        assertThrows(NotFoundException.class, () ->  Optional
                .ofNullable(underTest.findByType("color")));
    }


}