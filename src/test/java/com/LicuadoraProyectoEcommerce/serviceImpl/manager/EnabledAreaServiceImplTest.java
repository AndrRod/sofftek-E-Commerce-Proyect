package com.LicuadoraProyectoEcommerce.serviceImpl.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.manager.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.manager.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class EnabledAreaServiceImplTest {
    @Mock
    private EnableAreaRepository repository;
    @InjectMocks
    private EnabledAreaServiceImpl underTest;
    @Mock
    private UserAuthService userAuthService;
    @Mock
    private MessageHandler ms;
    @Mock
    private EnableAreaMapper mp;

    EnabledAreaCompleteDto enabledAreaDtoTest =  EnabledAreaCompleteDto.builder().id(1L).name("parte total").build();
    EnabledAreaDto enabledAreaFormDtoTest =  EnabledAreaDto.builder().id(1L).name("parte total").build();
    EnabledArea enabledAreaTest = EnabledArea.builder().id(1L).name("parte total").sellerProducts(new ArrayList<>()).baseProducts(new ArrayList<>()).customizations(new ArrayList<>()).customizationsAllowed(new ArrayList<>()).build();
    @Test
    void findEntityById() {
        when(repository.findById(1L)).thenReturn(Optional.of(enabledAreaTest));
        assert underTest.findEntityById(1L).equals(enabledAreaTest);
    }
    @Test
    public void notFoundEntityById() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        when(ms.message("not.found", String.valueOf(1L))).thenReturn("message");
        assertThrows(NotFoundException.class, () -> Optional
                .ofNullable(underTest.findEntityById(1L)));
    }
    @Test
    void createEntity() {
        when(mp.getDtoFromEntity(enabledAreaTest)).thenReturn(enabledAreaFormDtoTest);
        when(repository.save(any())).thenReturn(enabledAreaTest);
        EnabledAreaDto entityCreated = underTest.createEntity(enabledAreaFormDtoTest);
        assertThat(entityCreated).usingRecursiveComparison().isEqualTo(enabledAreaTest);
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(enabledAreaTest));
        when(mp.getCompleteDtoFromEntity(enabledAreaTest)).thenReturn(enabledAreaDtoTest);
        assert underTest.findById(1L).equals(enabledAreaDtoTest);
    }

    @Test
    void deleteEntityById() {
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(repository.findById(anyLong())).thenReturn(Optional.of(enabledAreaTest));
        doNothing().when(repository).delete(any());
        assertThat(underTest.deleteEntityById(1L)).isEqualTo(new HashMap<String, String>(){{put("Message", "message");}});
    }

    @Test
    void findDtoListPagination() {
        Page<EnabledArea> pageList = new PageImpl(Arrays.asList(enabledAreaTest));
        when(mp.getListCompleteDtoFromListEntity(Arrays.asList(enabledAreaTest))).thenReturn(Arrays.asList(enabledAreaDtoTest));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(pageList);
        List<EnabledAreaCompleteDto> listService = underTest.findDtoListPagination(0);
        assertThat(listService.size()).isEqualTo(pageList.getSize());
    }

    @Test
    void updateEntity() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(enabledAreaTest));
        when(mp.updateEntityFrom(enabledAreaTest, enabledAreaFormDtoTest)).thenReturn(enabledAreaTest);
        when(repository.save(enabledAreaTest)).thenReturn(enabledAreaTest);
        when(mp.getDtoFromEntity(enabledAreaTest)).thenReturn(enabledAreaFormDtoTest);
        EnabledAreaDto updateEntity = underTest.updateEntity(1L, enabledAreaFormDtoTest);
        assertThat(updateEntity).usingRecursiveComparison().isEqualTo(enabledAreaDtoTest);
    }

    @Test
    void findByName() {
        when(repository.findByName("todo el producto")).thenReturn(Optional.of(enabledAreaTest));
        assert underTest.findByName("todo el producto").equals(enabledAreaTest);
    }
    @Test
    void notFoundByName(){
        when(repository.findByName("todo el producto")).thenReturn(Optional.empty());
        when(ms.message("not.found.name", "todo el producto")).thenReturn("message");
        assertThrows(NotFoundException.class, () ->  Optional
                .ofNullable(underTest.findByName("todo el producto")));
    }}