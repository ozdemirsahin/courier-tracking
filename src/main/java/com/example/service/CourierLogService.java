package com.example.service;

import com.example.model.dto.CourierDTO;
import com.example.model.dto.CourierLogDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourierLogService {
    void create(CourierDTO courierLogDTO);

    Optional<CourierLogDTO> findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(LocalDateTime date, String courierId, String storeId);

    List<CourierLogDTO> findByCourierId(String courierId);
}
