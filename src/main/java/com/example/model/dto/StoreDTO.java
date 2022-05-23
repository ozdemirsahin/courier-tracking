package com.example.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StoreDTO {
    @NotNull(message = "Cannot be blank")
    private String id;
    @NotNull(message = "Cannot be blank")
    private String name;
    @NotNull(message = "Cannot be blank")
    private String city;
    @NotNull(message = "Cannot be blank")
    private Double lat;
    @NotNull(message = "Cannot be blank")
    private Double lng;
}
