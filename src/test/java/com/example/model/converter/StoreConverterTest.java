package com.example.model.converter;

import com.example.data.entity.Store;
import com.example.model.dto.StoreDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StoreConverterTest {
    private  StoreConverter storeConverter;

    private StoreDTO storeDTO;

    private Store store;

    @BeforeEach
    public void setup(){
        storeConverter= new StoreConverter();

        String id= UUID.randomUUID().toString();
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
    }

    @Test
    void test_toDTO() {
       StoreDTO result=storeConverter.toDTO(store);
       assertEquals(result, storeDTO);
    }

    @Test
    void test_toEntity() {
        Store result=storeConverter.toEntity(storeDTO);
        assertEquals(result, store);
    }
}