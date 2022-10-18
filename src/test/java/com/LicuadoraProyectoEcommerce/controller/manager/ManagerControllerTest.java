package com.LicuadoraProyectoEcommerce.controller.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.serviceImpl.manager.ManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
class ManagerControllerTest {
    protected MockMvc mockMvc;
    @Mock
    private ManagerServiceImpl managerService;
    @InjectMocks
    private ManagerController managerController;
    ManagerDto managerDtoTest = ManagerDto.builder().id(1l).name("Andres").email("andres@gmail.com").build();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }
    @Test
    void getDtoById() throws Exception {
        when(managerService.findById(1L)).thenReturn(managerDtoTest);
        mockMvc.perform(get("/manager/{id}", 1L)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("Andres"))
                        .andDo(print());
    }
    @Test
    void getExceptionNotFoundById() throws Exception {
        when(managerService.findById(1L)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/manager/{id}", 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    void getDtoListPage() throws Exception {
        when(managerService.getListEntityPage(anyInt())).thenReturn(Arrays.asList(managerDtoTest));
        mockMvc.perform(get("/manager")
                        .param("page", "0")
                        .contentType(APPLICATION_JSON))
                      .andExpect(status().isOk())
                     .andDo(print());
    }
}