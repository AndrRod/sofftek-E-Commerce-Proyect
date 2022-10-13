package com.LicuadoraProyectoEcommerce.serviceImpl.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.manager.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.mapper.manager.ManagerMapper;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import org.junit.Before;
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
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ManagerServiceImplTest {
    @Mock
    private ManagerRepository repository;
    @InjectMocks
    private ManagerServiceImpl underTest;
    @Mock
    private ManagerMapper mp;
    @Mock
    private MessageHandler ms;
    User userTest = User.builder().id(1L).name("Andres").email("andres@gmail.com").build();
    Manager managerTest = Manager.builder().id(1L).user(userTest).build();
    ManagerDto managerDtoTest = ManagerDto.builder().id(1l).name("Andres").email("andres@gmail.com").build();
    @Test
    void findById() {
        when(mp.getDtoFromEntity(managerTest)).thenReturn(managerDtoTest);
        when(repository.findById(1L)).thenReturn(Optional.of(managerTest));
        assert underTest.findById(1L).equals(managerDtoTest);
    }

    @Test
    void deleteById() {
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(repository.findById(anyLong())).thenReturn(Optional.of(managerTest));
        doNothing().when(repository).delete(any());
        assertThat(underTest.deleteById(1L)).isEqualTo(new HashMap<String, String>(){{put("Message", "message");}});
    }

    @Test
    void getListEntityPage() {
        Page<Manager> pageList = new PageImpl(Arrays.asList(managerTest));
        when(mp.listDtoFromListEntities(Arrays.asList(managerTest))).thenReturn(Arrays.asList(managerDtoTest));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(pageList);
        List<ManagerDto> listService = underTest.getListEntityPage(0);
        assertThat(listService.size()).isEqualTo(pageList.getSize());
    }

    @Test
    void findEntityById() {
        when(repository.findById(1L)).thenReturn(Optional.of(managerTest));
        assert underTest.findEntityById(1L).equals(managerTest);
    }
    @Test
    void notFoundEntityById(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        when(ms.message("not.found", String.valueOf(1L))).thenReturn("message");
        assertThrows(NotFoundException.class, () -> Optional
                .ofNullable(underTest.findEntityById(1L)));
    }
}