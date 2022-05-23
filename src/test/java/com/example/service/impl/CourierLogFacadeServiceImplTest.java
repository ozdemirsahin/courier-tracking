package com.example.service.impl;

import com.example.data.entity.Courier;
import com.example.data.entity.CourierLog;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.CourierLogDTO;
import com.example.model.dto.MoveCourierDTO;
import com.example.service.CourierLogService;
import com.example.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierLogFacadeServiceImplTest {
    @Spy
    @InjectMocks
    private CourierLogFacadeServiceImpl courierLogFacadeService;

    @Mock
    private CourierLogService courierLogService;

    @Mock
    private StoreService storeService;

    private CourierLogDTO courierLogDTO;

    private List<CourierLogDTO> courierLogDTOs;

    @BeforeEach
    public void setup(){
        String courierId= UUID.randomUUID().toString();
        String storeId= UUID.randomUUID().toString();
        String courierLogId= UUID.randomUUID().toString();
        LocalDateTime lastMovingTime=LocalDateTime.now();

        courierLogDTO = CourierLogDTO.builder()
                .id(courierLogId)
                .courierId(courierId)
                .storeId(storeId)
                .lastMovingTime(lastMovingTime)
                .build();

        courierLogDTOs= new ArrayList<>();
        courierLogDTOs.add(courierLogDTO);
    }

    @Test
    void test_getCourierLogsByCourierId() {
        when(courierLogService.findByCourierId(courierLogDTO.getCourierId())).thenReturn(courierLogDTOs);
        doNothing().when(courierLogFacadeService).fillCourierLogDetailInformation(courierLogDTOs);

        courierLogFacadeService.getCourierLogsByCourierId(courierLogDTO.getCourierId());

        verify(courierLogService).findByCourierId(courierLogDTO.getCourierId());
    }
}