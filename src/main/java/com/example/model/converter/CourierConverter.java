package com.example.model.converter;

import com.example.data.entity.Courier;
import com.example.model.dto.CourierDTO;
import org.springframework.stereotype.Component;

@Component
public class CourierConverter {

    public CourierDTO toDTO(Courier entity) {
        return CourierDTO.builder()
                .id(entity.getId())
                .lat(entity.getLat())
                .lng(entity.getLng())
                .personalNo(entity.getPersonalNo())
                .totalDistance(entity.getTotalDistance())
                .build();
    }

    public Courier toEntity(CourierDTO dto) {
        return Courier.builder()
                .id(dto.getId())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .personalNo(dto.getPersonalNo())
                .totalDistance(dto.getTotalDistance())
                .build();
    }
}
