package com.example.service.impl;

import com.example.exception.StoreNotFoundException;
import com.example.model.dto.CourierLogDTO;
import com.example.model.dto.StoreDTO;
import com.example.service.CourierLogFacadeService;
import com.example.service.CourierLogService;
import com.example.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierLogFacadeServiceImpl implements CourierLogFacadeService {

    private final CourierLogService courierLogService;
    private final StoreService storeService;

    @Override
    public List<CourierLogDTO> getCourierLogsByCourierId(String courierId) {
        List<CourierLogDTO> courierLogDTOs = courierLogService.findByCourierId(courierId);
        fillCourierLogDetailInformation(courierLogDTOs);
        return courierLogDTOs;
    }

    public void fillCourierLogDetailInformation(List<CourierLogDTO> courierLogDTOs) {

        if(CollectionUtils.isEmpty(courierLogDTOs)){
            return;
        }

        Map<String, String> storeMap = new HashMap<>();

        courierLogDTOs.forEach(courierLogDTO -> {
            if(storeMap.containsKey(courierLogDTO.getStoreId())){
                courierLogDTO.setStoreName(storeMap.get(courierLogDTO.getStoreId()));
            } else {
                Optional<StoreDTO> optionalStoreDTO = storeService.findById(courierLogDTO.getStoreId());
                if(optionalStoreDTO.isPresent()){
                    courierLogDTO.setStoreName(optionalStoreDTO.get().getName());
                    storeMap.put(optionalStoreDTO.get().getId(), optionalStoreDTO.get().getName());
                } else {
                    throw new StoreNotFoundException("Error occured while fetching store with id: " + courierLogDTO.getStoreId());
                }
            }
            courierLogDTO.setId(null);
            courierLogDTO.setCourierId(null);
            courierLogDTO.setStoreId(null);
        });

    }
}
