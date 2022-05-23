package com.example.model.converter;

import com.example.data.entity.Courier;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.MoveCourierDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourierConverterTest {

    private CourierConverter courierConverter;

    private CourierDTO courierDTO;

    private Courier courier;

    @BeforeEach
    public void setup(){
        courierConverter = new CourierConverter();

        String id= UUID.randomUUID().toString();
        String personalNumber="1234abc";
        double totalDistance=0.0;
        double lat=40.9923307;
        double lng=29.1244229;

        courierDTO= CourierDTO.builder()
                .id(id)
                .lat(lat)
                .lng(lng)
                .personalNo(personalNumber)
                .totalDistance(totalDistance)
                .build();

        courier= Courier.builder()
                .id(id)
                .lat(lat)
                .lng(lng)
                .personalNo(personalNumber)
                .totalDistance(totalDistance)
                .build();
    }

    @Test
    void test_toDTO() {
        CourierDTO result=courierConverter.toDTO(courier);
        assertEquals(result, courierDTO);
    }

    @Test
    void test_toEntity() {
        Courier result=courierConverter.toEntity(courierDTO);
        assertEquals(result, courier);
    }
}