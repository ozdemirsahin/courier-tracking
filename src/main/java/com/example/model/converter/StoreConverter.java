package com.example.model.converter;


import com.example.data.entity.Store;
import com.example.model.dto.StoreDTO;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {

    public StoreDTO toDTO(Store entity) {
        return StoreDTO.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .lat(entity.getLat())
                .lng(entity.getLng())
                .name(entity.getName())
                .build();
    }

    public Store toEntity(StoreDTO dto) {
        return Store.builder()
                .id(dto.getId())
                .city(dto.getCity())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .name(dto.getName())
                .build();
    }
}
