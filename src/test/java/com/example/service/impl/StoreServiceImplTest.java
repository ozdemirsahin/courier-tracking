package com.example.service.impl;

import com.example.data.entity.Store;
import com.example.data.repository.StoreRepository;
import com.example.model.converter.StoreConverter;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.StoreDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    @InjectMocks
    private StoreServiceImpl storeService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreConverter storeConverter;

    private StoreDTO storeDTO;

    private Store store;

    private List<Store> stores;

    private List<StoreDTO> storeDTOs;

    @BeforeEach
    public void setup(){
        String id=UUID.randomUUID().toString();
        String name="Ataşehir MMM Migros";
        String city="Ataşehir";
        double lat=40.9923307;
        double lng=29.1244229;

        storeDTO = StoreDTO.builder()
                .id(id)
                .lat(lat)
                .lng(lng)
                .name(name)
                .city(city)
                .build();

        store= Store.builder()
                .id(id)
                .city(city)
                .lat(lat)
                .lng(lng)
                .name(name)
                .build();

        stores= new ArrayList<>();
        stores.add(store);

        storeDTOs= new ArrayList<>();
        storeDTOs.add(storeDTO);
    }

    @Test
    void test_createAll() {

        when(storeConverter.toEntity(storeDTO)).thenReturn(store);
        when(storeRepository.saveAll(stores)).thenReturn(stores);

        storeService.createAll(storeDTOs);

        verify(storeConverter).toEntity(storeDTO);
        verify(storeRepository).saveAll(stores);
    }

    @Test
    void test_findAll() {
        when(storeRepository.findAll()).thenReturn(stores);
        when(storeConverter.toDTO(store)).thenReturn(storeDTO);

        List<StoreDTO> resultList=storeService.findAll();

        verify(storeRepository).findAll();
        verify(storeConverter).toDTO(store);

        assertEquals(resultList.get(0),storeDTO);
    }

    @Test
    void test_findById() {
        when(storeRepository.findById(storeDTO.getId())).thenReturn(Optional.ofNullable(store));
        when(storeConverter.toDTO(store)).thenReturn(storeDTO);

        Optional<StoreDTO> optionalResult=storeService.findById(storeDTO.getId());

        verify(storeRepository).findById(storeDTO.getId());
        verify(storeConverter).toDTO(store);

        assertEquals(optionalResult.get(),storeDTO);

    }
}