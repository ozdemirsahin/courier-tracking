package com.example.service.impl;

import com.example.data.entity.Courier;
import com.example.data.entity.CourierLog;
import com.example.data.entity.Store;
import com.example.data.repository.CourierLogRepository;
import com.example.model.converter.CourierLogConverter;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.CourierLogDTO;
import com.example.model.dto.StoreDTO;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourierLogServiceImplTest {
    @Spy
    @InjectMocks
    private CourierLogServiceImpl courierLogService;

    @Mock
    private CourierLogRepository courierLogRepository;

    @Mock
    private StoreService storeService;

    @Mock
    private CourierLogConverter courierLogConverter;


    private CourierLogDTO courierLogDTO;

    private CourierLog courierLog;


    private List<CourierLog> courierLogs;

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

        courierLog = CourierLog.builder()
                .id(courierLogId)
                .courierId(courierId)
                .storeId(storeId)
                .lastMovingTime(lastMovingTime)
                .build();

        courierLogs=new ArrayList<>();
        courierLogs.add(courierLog);

    }

    @Test
    void test_findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals() {
        LocalDateTime dateTime= LocalDateTime.now();
        String storeId= UUID.randomUUID().toString();
        Optional<CourierLog> optionalCourierLog= Optional.ofNullable(courierLog);
        when(courierLogRepository.findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(dateTime, courierLogDTO.getCourierId(), storeId)).thenReturn(optionalCourierLog);
        when(courierLogConverter.toDTO(courierLog)).thenReturn(courierLogDTO);

        courierLogService.findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals(dateTime, courierLogDTO.getCourierId(), storeId);

        verify(courierLogRepository).findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(dateTime, courierLogDTO.getCourierId(), storeId);
        verify(courierLogConverter).toDTO(courierLog);
    }

    @Test
    void test_findByCourierId() {
        when(courierLogRepository.findCourierLogsByCourierIdOrderByLastMovingTimeDesc(courierLogDTO.getCourierId())).thenReturn(courierLogs);
        when(courierLogConverter.toDTO(courierLog)).thenReturn(courierLogDTO);

        courierLogService.findByCourierId(courierLogDTO.getCourierId());

        verify(courierLogRepository).findCourierLogsByCourierIdOrderByLastMovingTimeDesc(courierLogDTO.getCourierId());
        verify(courierLogConverter).toDTO(courierLog);
    }
}