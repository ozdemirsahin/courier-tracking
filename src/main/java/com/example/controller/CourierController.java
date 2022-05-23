package com.example.controller;

import com.example.model.dto.CourierDTO;
import com.example.model.dto.CourierLogDTO;
import com.example.model.dto.MoveCourierDTO;
import com.example.service.CourierLogFacadeService;
import com.example.service.CourierLogService;
import com.example.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/courier")
public class CourierController {

    private final CourierService courierService;
    private final CourierLogService courierLogService;
    private final CourierLogFacadeService courierLogFacadeService;

    @PostMapping(value = "/add")
    public ResponseEntity<CourierDTO> add(@RequestBody @Valid CourierDTO courierDTO) {
        return ResponseEntity.ok(courierService.add(courierDTO));
    }

    @PostMapping (value = "/move")
    public ResponseEntity<CourierDTO> move(@RequestBody @Valid MoveCourierDTO moveCourierDTO) {
        return ResponseEntity.ok(courierService.move(moveCourierDTO));
    }

    @GetMapping (value = "/{id}/totalTravelDistance")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable ("id") String courierId) {
        CourierDTO courierDTO = courierService.findByCourierId(courierId);
        return ResponseEntity.ok(courierDTO.getTotalDistance());
    }

    @GetMapping(value = "/{id}/courier-log")
    public ResponseEntity<List<CourierLogDTO>> findLogsByCourierId(@PathVariable ("id") String courierId) {
        return ResponseEntity.ok(courierLogFacadeService.getCourierLogsByCourierId(courierId));
    }

}
