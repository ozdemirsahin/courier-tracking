package com.example.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourierLogDTO {

    private String id;
    @NotNull(message = "Cannot be blank")
    private String courierId;
    @NotNull (message = "Cannot be blank")
    private String storeId;
    private String storeName;
    private LocalDateTime lastMovingTime;

}