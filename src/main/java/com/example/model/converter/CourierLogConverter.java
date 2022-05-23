package com.example.model.converter;

import com.example.data.entity.CourierLog;
import com.example.model.dto.CourierLogDTO;
import org.springframework.stereotype.Component;

@Component
public class CourierLogConverter {

    public CourierLogDTO toDTO(CourierLog entity) {
        return CourierLogDTO.builder()
                .id(entity.getId())
                .courierId(entity.getCourierId())
                .storeId(entity.getStoreId())
                .lastMovingTime(entity.getLastMovingTime())
                .build();
    }

    public CourierLog toEntity(CourierLogDTO dto) {
        return CourierLog.builder()
                .id(dto.getId())
                .courierId(dto.getCourierId())
                .storeId(dto.getStoreId())
                .lastMovingTime(dto.getLastMovingTime())
                .build();
    }
}
