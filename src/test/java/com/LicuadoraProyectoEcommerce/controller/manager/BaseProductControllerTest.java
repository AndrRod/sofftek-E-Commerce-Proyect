package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.serviceImpl.manager.BaseProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class BaseProductControllerTest {
    protected MockMvc mockMvc;
    @Mock
    private MessageHandler ms;
    @Mock
    private BaseProductServiceImpl baseProductService;
    @InjectMocks
    private BaseProductController baseProductController;
    @Autowired
    private ObjectMapper objectMapper;
    BaseProductDtoComplete baseProductDtoTest = BaseProductDtoComplete.builder().id(1L).name("zapato").price(100d).daysToManufacture(12).build();
    BaseProductDto baseProductCreateDtoTest = BaseProductDto.builder().id(1L).name("zapato").price(100d).daysToManufacture(12).build();
    @Mock
    HttpServletRequest request;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(baseProductController).build();
        request =new MockHttpServletRequest();
    }
    @Test
    void createProduct()throws Exception {
        when(baseProductService.createBaseProduct(baseProductCreateDtoTest, request)).thenReturn(baseProductDtoTest);
        mockMvc.perform(post("/manager/product")
                        .content(new ObjectMapper().writeValueAsString(baseProductCreateDtoTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                        .andExpect(status().isCreated())
                        .andDo(print());
    }

    @Test
    void getProductById()  throws Exception {
        when(baseProductService.getBaseProductById(anyLong())).thenReturn(baseProductDtoTest);
        mockMvc.perform(get("/manager/product/{id}", 1)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zapato"))
                .andDo(print());
    }
    @Test
    void notFoundExceptionGetProductById()throws Exception {
        when(baseProductService.getBaseProductById(1L)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/manager/product/{id}", 1)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void getProductPage() throws Exception {
        when(baseProductService.getBaseProductListPage(anyInt())).thenReturn(Arrays.asList(baseProductDtoTest));
        mockMvc.perform(get("/manager/product")
                        .param("page", "0")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void getListEntityPaginationWithoutParam() throws Exception {
        when(baseProductService.getBaseProductListPage(anyInt())).thenReturn(Arrays.asList(baseProductDtoTest));
        mockMvc.perform(get("/manager/product")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    void deleteProductById()  throws Exception {
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(baseProductService.deleteBaseProductById(1l)).thenReturn(Map.of("Message", "message"));
        mockMvc.perform(delete("/manager/product/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
    @Test
    void exceptionForDeleteById() throws Exception {
        when(ms.message("cant.delete", null)).thenReturn("message");
        when(baseProductService.deleteBaseProductById(1l)).thenThrow(BadRequestException.class);
        mockMvc.perform(delete("/manager/product/{id}", 1)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void updateProduct()  throws Exception {
        when(baseProductService.updateBaseProduct(1l, baseProductCreateDtoTest, request)).thenReturn(baseProductDtoTest);
        mockMvc.perform(put("/manager/product/{id}", "1")
                        .content(objectMapper.writeValueAsString(baseProductCreateDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    void addAreaToProduct() throws Exception {
        when(baseProductService.addEnabledAreaToEntity(1l, 1l, request)).thenReturn(baseProductDtoTest);
        mockMvc.perform(post("/manager/product/{id}/area/{idArea}", 1l, 1l)
                        .content(new ObjectMapper().writeValueAsString(baseProductCreateDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    void removeAreaToProduct() throws Exception {
        when(baseProductService.addEnabledAreaToEntity(1l, 1l, request)).thenReturn(baseProductDtoTest);
        mockMvc.perform(delete("/manager/product/{id}/area/{idArea}", 1l, 1l)
                        .content(new ObjectMapper().writeValueAsString(baseProductCreateDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
}