package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.serviceImpl.manager.CustomizationAllowedServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Map;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CustomizationAllowedControllerTest {

    protected MockMvc mockMvc;
    @Mock
    private MessageHandler ms;
    @Mock
    private CustomizationAllowedServiceImpl customizationAllowedService;
    @InjectMocks
    private CustomizationAllowedController customizationAllowedControllerTest;
    @Autowired
    private ObjectMapper objectMapper;

    CustomizationAllowedDto custDtoTest =  CustomizationAllowedDto.builder().id(1L).type("color").build();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customizationAllowedControllerTest).build();
    }

    @Test
    void getEntityById() throws Exception {
        when(customizationAllowedService.findDtoById(anyLong())).thenReturn(custDtoTest);
        mockMvc.perform(get("/manager/customization/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.type").value("color"))
                        .andDo(print());
    }
    @Test
    void getExceptionNotFoundById() throws Exception {
        when(customizationAllowedService.findDtoById(1L)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/manager/customization/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andDo(print());
    }

    @Test
    void getListEntityPagination() throws Exception {
        when(customizationAllowedService.findDtoListPagination(anyInt())).thenReturn(Arrays.asList(custDtoTest));
        mockMvc.perform(get("/manager/customization")
                        .param("page", "0")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
    @Test
    void getListEntityPaginationWithoutParam() throws Exception {
        when(customizationAllowedService.findDtoListPagination(anyInt())).thenReturn(Arrays.asList(custDtoTest));
        mockMvc.perform(get("/manager/customization")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(customizationAllowedService.deleteEntityById(1l)).thenReturn(Map.of("Message", "message"));
        mockMvc.perform(delete("/manager/customization/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
    @Test
    void exceptionForDeleteById() throws Exception {
        when(ms.message("cant.delete", null)).thenReturn("message");
        when(customizationAllowedService.deleteEntityById(1l)).thenThrow(BadRequestException.class);
        mockMvc.perform(delete("/manager/customization/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
    }
    @Test
    void createEntity() throws Exception {
        when(customizationAllowedService.createEntity(custDtoTest)).thenReturn(custDtoTest);
        mockMvc.perform(post("/manager/customization")
                        .content(objectMapper.writeValueAsString(custDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.type").value("color"))
                        .andDo(print());
    }

    @Test
    void ExceptionConstraintCreateEntity() throws Exception {
        when(customizationAllowedService.createEntity(custDtoTest)).thenReturn(custDtoTest);
        mockMvc.perform(post("/manager/customization")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
    }

    @Test
    void updateEntity() throws Exception {
        when(customizationAllowedService.updateEntity(1l, custDtoTest)).thenReturn(custDtoTest);
        mockMvc.perform(put("/manager/customization/{id}", "1")
                        .content(objectMapper.writeValueAsString(custDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.type").value("color"))
                        .andDo(print());
    }
}