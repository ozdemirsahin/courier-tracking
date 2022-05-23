package com.example.model.converter;

import com.example.data.entity.CourierLog;
import com.example.model.dto.CourierLogDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourierLogConverterTest {

    private CourierLogConverter courierLogConverter;

    private CourierLogDTO courierLogDTO;

    private CourierLog courierLog;


    @BeforeEach
    public void setup(){
        courierLogConverter= new CourierLogConverter();

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

    }

    @Test
    void test_toDTO() {
        CourierLogDTO result=courierLogConverter.toDTO(courierLog);
        assertEquals(result, courierLogDTO);
    }

    @Test
    void test_toEntity() {
        CourierLog result=courierLogConverter.toEntity(courierLogDTO);
        assertEquals(result, courierLog);
    }
}