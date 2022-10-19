package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.serviceImpl.manager.EnabledAreaServiceImpl;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AreaEnabledControllerTest {

    protected MockMvc mockMvc;
    @Mock
    private MessageHandler ms;
    @Mock
    private EnabledAreaServiceImpl enabledAreaService;
    @InjectMocks
    private AreaEnabledController enabledControllerTest;
    @Autowired
    private ObjectMapper objectMapper;

    EnabledAreaCompleteDto enabledAreaDtoTest =  EnabledAreaCompleteDto.builder().id(1L).name("parte total").build();
    EnabledAreaDto enabledAreaFormDtoTest =  EnabledAreaDto.builder().id(1L).name("parte total").build();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(enabledControllerTest).build();
    }

    @Test
    void findEntityById() throws Exception {
        when(enabledAreaService.findById(anyLong())).thenReturn(enabledAreaDtoTest);
        mockMvc.perform(get("/manager/area/{id}", 1)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("parte total"))
                .andDo(print());
    }

    @Test
    void findEntitiesListPage() throws Exception {
        when(enabledAreaService.findDtoListPagination(anyInt())).thenReturn(Arrays.asList(enabledAreaDtoTest));
        mockMvc.perform(get("/manager/area")
                        .param("page", "0")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
    @Test
    void getExceptionNotFoundById() throws Exception {
        when(enabledAreaService.findById(1L)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/manager/area/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andDo(print());
    }

    @Test
    void createEntity() throws Exception{
        when(enabledAreaService.createEntity(enabledAreaFormDtoTest)).thenReturn(enabledAreaFormDtoTest);
        mockMvc.perform(post("/manager/area")
                        .content(objectMapper.writeValueAsString(enabledAreaDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name").value("parte total"))
                        .andDo(print());
    }
    @Test
    void ExceptionConstraintCreateEntity() throws Exception {
        when(enabledAreaService.createEntity(enabledAreaFormDtoTest)).thenReturn(enabledAreaFormDtoTest);
        mockMvc.perform(post("/manager/area")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
    }


    @Test
    void updateEntity() throws Exception{
        when(enabledAreaService.updateEntity(1l, enabledAreaFormDtoTest)).thenReturn(enabledAreaFormDtoTest);
        mockMvc.perform(put("/manager/area/{id}", "1")
                        .content(objectMapper.writeValueAsString(enabledAreaFormDtoTest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("parte total"))
                        .andDo(print());
    }

    @Test
    void deleteById() throws Exception{
        when(ms.message("delete.success", String.valueOf(1L))).thenReturn("message");
        when(enabledAreaService.deleteEntityById(1l)).thenReturn(Map.of("Message", "message"));
        mockMvc.perform(delete("/manager/area/{id}", 1)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void exceptionForDeleteById() throws Exception {
        when(ms.message("cant.delete", null)).thenReturn("message");
        when(enabledAreaService.deleteEntityById(1l)).thenThrow(BadRequestException.class);
        mockMvc.perform(delete("/manager/area/{id}", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
    }
}