package com.example.service.impl;

import com.example.data.entity.Courier;
import com.example.data.repository.CourierRepository;
import com.example.exception.CourierExistException;
import com.example.exception.CourierNotFoundException;
import com.example.model.converter.CourierConverter;
import com.example.model.dto.CourierDTO;
import com.example.model.dto.MoveCourierDTO;
import com.example.service.CourierLogService;
import com.example.service.CourierService;
import com.example.util.DistanceCalculationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    private final CourierLogService courierLogService;

    private final CourierConverter courierConverter;

    @Override
    @Transactional
    public CourierDTO add(CourierDTO courierDTO) {

        Optional<Courier> optionalCourier = courierRepository.findCourierByPersonalNo(courierDTO.getPersonalNo());
        if(optionalCourier.isPresent()){
            throw new CourierExistException("Courier already exist!");
        }

        courierDTO.setId(UUID.randomUUID().toString());

        if(ObjectUtils.isEmpty(courierDTO.getTotalDistance())){
            courierDTO.setTotalDistance(0.0);
        }

        Courier courier = courierConverter.toEntity(courierDTO);
        CourierDTO createdCourierDTO = courierConverter.toDTO(courierRepository.save(courier));
        courierLogService.create(createdCourierDTO);

        return createdCourierDTO;
    }

    @Override
    @Transactional
    public CourierDTO move(MoveCourierDTO moveCourierDTO) {

        Optional<Courier> optionalCourier = courierRepository.findCourierByPersonalNo(moveCourierDTO.getPersonalNo());
        if(!optionalCourier.isPresent()){
            throw new CourierNotFoundException("Courier not found, courierId:" + moveCourierDTO.getPersonalNo());
        }
        Courier courier = optionalCourier.get();
        CourierDTO courierDTO = courierConverter.toDTO(courier);
        courierDTO.setLat(moveCourierDTO.getLat());
        courierDTO.setLng(moveCourierDTO.getLng());
        double newDistance = DistanceCalculationUtil.calculateDistanceBetweenTwoPoints(moveCourierDTO.getLat(), moveCourierDTO.getLng(), courier.getLat(), courier.getLng());
        courierDTO.setTotalDistance(courierDTO.getTotalDistance() + newDistance);
        log.info("new total distance for courier: " + courierDTO.getPersonalNo() + " is" + courierDTO.getTotalDistance());
        courierRepository.save(courierConverter.toEntity(courierDTO));
        courierLogService.create(courierDTO);
        return courierDTO;
    }

    @Override
    public CourierDTO findByCourierId(String courierId) {
        Optional<Courier> optional = courierRepository.findById(courierId);
        if(!optional.isPresent()){
            throw new CourierNotFoundException("Cannot find courier with id: " + courierId);
        }
        return courierConverter.toDTO(optional.get());
    }

}
