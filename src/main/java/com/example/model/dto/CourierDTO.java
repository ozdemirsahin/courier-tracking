package com.example.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourierDTO {
        private String id;
        @NotNull(message = "Cannot be blank")
        private String personalNo;
        @NotNull(message = "Cannot be blank")
        private Double lat;
        @NotNull(message = "Cannot be blank")
        private Double lng;
        private Double totalDistance;
}
