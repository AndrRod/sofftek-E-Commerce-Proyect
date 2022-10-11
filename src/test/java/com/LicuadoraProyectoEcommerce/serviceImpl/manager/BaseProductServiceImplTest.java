package com.LicuadoraProyectoEcommerce.serviceImpl.manager;
import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.manager.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.repository.manager.BaseProductRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class BaseProductServiceImplTest {

    @Mock
    private BaseProductRepository repository;
    @InjectMocks
    private BaseProductServiceImpl underTest;
    @Mock
    private UserAuthService userAuthService;
    @Mock
    private MessageHandler  ms;
    @Mock
    private BaseProductMapper mp;

    BaseProduct baseProductTest = BaseProduct.builder().id(1L).name("zapato").build();
    BaseProductDtoComplete baseProductDtoTest = BaseProductDtoComplete.builder().id(1L).name("zapato").build();
    BaseProductDto baseProductCreateDtoTest = BaseProductDto.builder().id(1L).name("zapato").build();
    User userTest = User.builder().id(1L).name("Andres").email("andres@gmail.com").build();
    Manager managerTest = Manager.builder().user(userTest).build();
    private MockHttpServletRequest request;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
    }
    @DisplayName("excepcion not found por producto encontrado")
    @Test
    public void foundEntityById() {
        when(repository.findById(1L)).thenReturn(Optional.of(baseProductTest));
        assert underTest.findEntityById(1L).equals(baseProductTest);
    }
    @DisplayName("producto no encontrado")
    @Test
    public void notFoundEntityById() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        when(ms.message("not.found", String.valueOf(1L))).thenReturn("message");
        assertThrows(NotFoundException.class, () -> Optional
                .ofNullable(underTest.findEntityById(1L)));
    }


    @DisplayName("obtener dto busando producto por id")
    @Test
    public void getBaseProductById() {
        when(mp.getDtoFromEntity(baseProductTest)).thenReturn(baseProductDtoTest);
        when(repository.findById(anyLong())).thenReturn(Optional.of(baseProductTest));
        BaseProductDtoComplete productServiceDto = underTest.getBaseProductById(1L);
        assertThat(productServiceDto).usingRecursiveComparison().isEqualTo(baseProductDtoTest);
    }

    @Test
    public void getBaseProductListPage() {
        Page<BaseProduct> pageList = new PageImpl(Arrays.asList(baseProductTest));
        when(mp.getListDtoCompleteFromListEntity(Arrays.asList(baseProductTest))).thenReturn(Arrays.asList(baseProductDtoTest));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(pageList);
        List<BaseProductDtoComplete> listService = underTest.getBaseProductListPage(0);
        assertThat(listService.size()).isEqualTo(pageList.getSize());
    }

    @Test
    public void deleteBaseProductById() {
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(repository.findById(anyLong())).thenReturn(Optional.of(baseProductTest));
        doNothing().when(repository).delete(any());
        assertThat(underTest.deleteBaseProductById(1L)).isEqualTo(new HashMap<String, String>(){{put("Message", "message");}});
    }

    @Test
    public void createBaseProduct() {
        when(mp.getEntityCreateFromDto(baseProductCreateDtoTest)).thenReturn(baseProductTest);
        when(mp.getDtoFromEntity(baseProductTest)).thenReturn(baseProductDtoTest);
        when(repository.save(any())).thenReturn(baseProductTest);
        when(userAuthService.findManagerLogged(request)).thenReturn(managerTest);

        BaseProductDtoComplete productService= underTest.createBaseProduct(baseProductCreateDtoTest, request);
        assertThat(productService).usingRecursiveComparison().isEqualTo(baseProductDtoTest);
    }
    @Test
    public void updateBaseProduct() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(baseProductTest));
        when(mp.getEntityUpdateFromDto(baseProductTest, baseProductCreateDtoTest)).thenReturn(baseProductTest);
        when(userAuthService.findManagerLogged(request)).thenReturn(managerTest);
        when(repository.save(baseProductTest)).thenReturn(baseProductTest);
        when(mp.getDtoFromEntity(baseProductTest)).thenReturn(baseProductDtoTest);

        BaseProductDtoComplete productService = underTest.updateBaseProduct(1L, baseProductCreateDtoTest, request);
        assertThat(productService).usingRecursiveComparison().isEqualTo(baseProductDtoTest);
    }


    @Test
    public void addEnabledAreaToEntity() {
    }

    @Test
    public void removeEnabledAreaToEntity() {
    }
}