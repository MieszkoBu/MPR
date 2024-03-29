package com.example.Lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    private MockMvc mockMvc;
    @Mock
    private MyRestService service;
    @InjectMocks
    private MyRestController controller;
    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new CarExceptionHandler(), controller
        ).build();
    }
    @Test
    public void getByIdReturns200WhenCarIsPresent() throws Exception {
        Car car = new Car("a", "v", 2009);
        Mockito.when(service.findByID(1L)).thenReturn(Optional.of(car));

        mockMvc.perform(MockMvcRequestBuilders.get("/findbyId/1"))
                .andExpect(jsonPath("$.marka").value("a"))
                .andExpect(jsonPath("$.model").value("v"))
                .andExpect(jsonPath("$.rok_produkcji").value(2009))
                .andExpect(status().isOk());
    }
    @Test
    public void check400IsReturnedWhenCarIsAlreadyThere() throws Exception {
        Mockito.doThrow(new CarExceptionHandler.CarAlreadyExistExeption())
                .when(service).save(any());
        mockMvc.perform(post("/Car/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"marka\": \"BMW\", \"model\": \"A\", \"rok_produkcji\": 2009}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
