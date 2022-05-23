package com.example.controller;

import com.example.model.dto.CourierDTO;
import com.example.model.dto.CourierLogDTO;
import com.example.model.dto.MoveCourierDTO;
import com.example.service.CourierLogFacadeService;
import com.example.service.CourierLogService;
import com.example.service.CourierService;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CourierControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CourierService courierService;

    @MockBean
    private CourierLogService courierLogService;

    @MockBean
    private CourierLogFacadeService courierLogFacadeService;

    @Autowired
    private Gson gson;

    private CourierDTO courierDTO;

    private final String courierId = UUID.randomUUID().toString();;

    @BeforeEach
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        courierDTO = CourierDTO.builder()
                .id(courierId)
                .personalNo("1231242ABC1231")
                .lat(40.986106)
                .lng(29.1161293)
                .totalDistance(0.0)
                .build();
    }

    @AfterEach
    public void afterTest()
    {
        verifyNoMoreInteractions(courierService);
    }

    @Test
    void test_add() throws Exception {

        when(courierService.add(courierDTO)).thenReturn(courierDTO);

        String url = "/courier/add";
        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(courierDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(courierDTO)));

        verify(courierService).add(any(CourierDTO.class));

    }

    @Test
    void test_move() throws  Exception{
        MoveCourierDTO moveCourierDTO = MoveCourierDTO.builder()
                .personalNo(UUID.randomUUID().toString())
                .lat(40.986106)
                .lng(29.1161293)
                .build();

        when(courierService.move(moveCourierDTO)).thenReturn(courierDTO);

        String url = "/courier/move";
        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(moveCourierDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(courierDTO)));

        verify(courierService).move(any(MoveCourierDTO.class));
    }

    @Test
    void test_getTotalDistance() throws Exception {

        when(courierService.findByCourierId(courierId)).thenReturn(courierDTO);

        String url = "/courier/"+ courierId +"/totalTravelDistance";
        this.mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(courierDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(courierDTO.getTotalDistance())));

        verify(courierService).findByCourierId(courierId);

    }

    @Test
    void test_findLogsByCourierId() throws Exception{

        List<CourierLogDTO> courierLogDTOList= new ArrayList<>();
        CourierLogDTO courierLogDTO = CourierLogDTO.builder()
                        .id("123abc")
                        .storeId("1234abcd")
                        .courierId(courierId)
                        .lastMovingTime(null)
                        .build();
        courierLogDTOList.add(courierLogDTO);

        when(courierLogFacadeService.getCourierLogsByCourierId(courierId)).thenReturn(courierLogDTOList);

        String url = "/courier/" + courierId + "/courier-log";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(courierLogDTOList)));

        verify(courierLogFacadeService).getCourierLogsByCourierId(courierId);

    }

}
