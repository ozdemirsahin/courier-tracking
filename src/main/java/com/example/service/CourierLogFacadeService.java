package com.example.service;

import com.example.model.dto.CourierLogDTO;

import java.util.List;

public interface CourierLogFacadeService {

    List<CourierLogDTO> getCourierLogsByCourierId(String courierID);

}
