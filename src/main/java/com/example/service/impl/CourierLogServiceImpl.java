package com.example.service.impl;

import com.example.data.entity.CourierLog;
import com.example.data.repository.CourierLogRepository;
import com.example.model.converter.CourierLogConverter;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.CourierLogDTO;
import com.example.model.dto.StoreDTO;
import com.example.service.CourierLogService;
import com.example.service.StoreService;
import com.example.util.DistanceCalculationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierLogServiceImpl implements CourierLogService {

    private static final int STORE_DISTANCE_TO_LOG = 100;
    private final CourierLogRepository courierLogRepository;
    private final StoreService storeService;

    private final CourierLogConverter courierLogConverter;

    @Override
    public void create(CourierDTO courierDTO) {
        List<StoreDTO> allStores = storeService.findAll();
        allStores.stream()
                .filter(storeDTO -> DistanceCalculationUtil.calculateDistanceBetweenTwoPoints(courierDTO.getLat(), courierDTO.getLng(), storeDTO.getLat(), storeDTO.getLng()) <= STORE_DISTANCE_TO_LOG)
                .findFirst()
                .ifPresent(storeDTO -> {
                    log.info("Courier is near to " + storeDTO.getName());

                    Optional<CourierLogDTO> optionalCourierLogDTO = findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(
                            LocalDateTime.now().minusMinutes(1), courierDTO.getId(), storeDTO.getId());

                    if(optionalCourierLogDTO.isPresent()){
                        log.warn("Courier is already logged for store in 1 minutes");
                    } else {
                        log.info("courier log created for courier:" + courierDTO.getPersonalNo() + ", store:" + storeDTO.getName());
                        CourierLogDTO courierLogDTO=CourierLogDTO.builder().id(UUID.randomUUID().toString()).courierId(courierDTO.getId()).storeId(storeDTO.getId()).lastMovingTime(LocalDateTime.now()).build();
                        CourierLog courierLog = courierLogConverter.toEntity(courierLogDTO);
                        courierLogRepository.save(courierLog);
                    }
                });
    }

    @Override
    public Optional<CourierLogDTO> findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(LocalDateTime date, String courierId, String storeId) {
        return courierLogRepository.findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(date, courierId, storeId).map(courierLogConverter::toDTO);
    }

    @Override
    public List<CourierLogDTO> findByCourierId(String courierId) {
        return courierLogRepository.findCourierLogsByCourierIdOrderByLastMovingTimeDesc(courierId).stream().map(courierLogConverter::toDTO).collect(Collectors.toList());
    }

}
