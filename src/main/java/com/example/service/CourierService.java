package com.example.service;

import com.example.model.dto.CourierDTO;
import com.example.model.dto.MoveCourierDTO;

public interface CourierService {

    CourierDTO add(CourierDTO courierDTO);

    CourierDTO move(MoveCourierDTO moveCourierDTO);

    CourierDTO findByCourierId(String personalId);

}
