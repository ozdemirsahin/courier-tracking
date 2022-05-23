package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MoveCourierDTO {
    @NotNull(message = "Cannot be blank")
    private String personalNo;
    @NotNull(message = "Cannot be blank")
    private Double lat;
    @NotNull(message = "Cannot be blank")
    private Double lng;
}
