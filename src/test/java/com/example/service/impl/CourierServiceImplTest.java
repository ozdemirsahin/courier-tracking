package com.example.service.impl;

import com.example.data.entity.Courier;
import com.example.data.repository.CourierRepository;
import com.example.exception.CourierExistException;
import com.example.exception.CourierNotFoundException;
import com.example.model.converter.CourierConverter;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.MoveCourierDTO;
import com.example.service.CourierLogService;
import com.example.util.DistanceCalculationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierServiceImplTest {

    @InjectMocks
    private CourierServiceImpl courierService;

    @Mock
    private CourierRepository courierRepository;

    @Mock
    private CourierLogService courierLogService;

    @Mock
    private CourierConverter courierConverter;

    @Spy
    private DistanceCalculationUtil distanceCalculationUtil;

    private CourierDTO courierDTO;

    private Courier courier;

    private MoveCourierDTO moveCourierDTO;

    @BeforeEach
    public void setup(){
        String id= UUID.randomUUID().toString();
        String personalNumber="1234abc";
        double totalDistance=0.0;
        double lat=40.9923307;
        double lng=29.1244229;

        courierDTO=CourierDTO.builder()
                    .id(id)
                    .lat(lat)
                    .lng(lng)
                    .personalNo(personalNumber)
                    .totalDistance(totalDistance)
                    .build();

        courier=Courier.builder()
                .id(id)
                .lat(lat)
                .lng(lng)
                .personalNo(personalNumber)
                .totalDistance(totalDistance)
                .build();

        moveCourierDTO=MoveCourierDTO.builder()
                        .lat(40.986106)
                        .lng(28.1161293)
                        .personalNo(personalNumber)
                        .build();
    }

    @Test
    void whenAddCalledWithNonExistCourier_itShouldReturnCourierDTO() {
        Optional<Courier> optionalCourier=Optional.empty();
        when(courierRepository.findCourierByPersonalNo(courierDTO.getPersonalNo())).thenReturn(optionalCourier);
        when(courierConverter.toEntity(courierDTO)).thenReturn(courier);
        when(courierRepository.save(courier)).thenReturn(courier);
        when(courierConverter.toDTO(courier)).thenReturn(courierDTO);
        doNothing().when(courierLogService).create(courierDTO);

        CourierDTO result=courierService.add(courierDTO);

        verify(courierRepository).findCourierByPersonalNo(courierDTO.getPersonalNo());
        verify(courierConverter).toEntity(courierDTO);
        verify(courierRepository).save(courier);
        verify(courierConverter).toDTO(courier);
        verify(courierLogService).create(courierDTO);

        assertEquals(result, courierDTO);
    }

    @Test()
    public void whenAddCalledWithExistCourier_itShouldThrowCourierExistException() {
        Optional<Courier> optionalCourier=Optional.ofNullable(courier);
        when(courierRepository.findCourierByPersonalNo(courierDTO.getPersonalNo())).thenReturn(optionalCourier);

        Exception exception = assertThrows(CourierExistException.class, () -> {
            courierService.add(courierDTO);
        });

        String expectedMessage = "Courier already exist!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void whenMoveCalledWithExistCourier_itShouldReturnCourierDTO() {
        Optional<Courier> optionalCourier=Optional.ofNullable(courier);
        when(courierRepository.findCourierByPersonalNo(courierDTO.getPersonalNo())).thenReturn(optionalCourier);
        when(courierConverter.toDTO(courier)).thenReturn(courierDTO);
        when(courierConverter.toEntity(courierDTO)).thenReturn(courier);
        when(courierRepository.save(courier)).thenReturn(courier);
        doNothing().when(courierLogService).create(courierDTO);

        CourierDTO result=courierService.move(moveCourierDTO);

        verify(courierRepository).findCourierByPersonalNo(courierDTO.getPersonalNo());
        verify(courierConverter).toDTO(courier);
        verify(courierConverter).toEntity(courierDTO);
        verify(courierRepository).save(courier);
        verify(courierLogService).create(courierDTO);

        assertEquals(result, courierDTO);
    }

    @Test
    void whenMoveCalledWithNonExistCourier_itShoulThrowCourierNotFoundException() {
        Optional<Courier> optionalCourier=Optional.empty();
        when(courierRepository.findCourierByPersonalNo(courierDTO.getPersonalNo())).thenReturn(optionalCourier);

        Exception exception = assertThrows(CourierNotFoundException.class, () -> {
            courierService.move(moveCourierDTO);
        });

        String expectedMessage = "Courier not found, courierId:" + moveCourierDTO.getPersonalNo();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenGetTotalDistanceCalledWithExistCourier_itShouldReturnCourierDto() {
        Optional<Courier> optionalCourier=Optional.ofNullable(courier);
        when(courierRepository.findById(courierDTO.getId())).thenReturn(optionalCourier);
        when(courierConverter.toDTO(courier)).thenReturn(courierDTO);

        CourierDTO result=courierService.findByCourierId(courierDTO.getId());

        verify(courierRepository).findById(courierDTO.getId());

        assertEquals(result, courierDTO);
    }

    @Test
    void whenGetTotalDistanceCalledWithNonExistCourier_itShoulThrowCourierNotFoundException() {
        Optional<Courier> optionalCourier=Optional.empty();
        when(courierRepository.findById(courierDTO.getId())).thenReturn(optionalCourier);

        Exception exception = assertThrows(CourierNotFoundException.class, () -> {
            courierService.findByCourierId(courierDTO.getId());
        });

        String expectedMessage = "Cannot find courier with id: " + courierDTO.getId();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}