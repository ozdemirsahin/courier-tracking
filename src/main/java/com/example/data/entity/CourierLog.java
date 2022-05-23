package com.example.data.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "courier_log")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourierLog {

    @Id
    @Column(name = "id")
    private String id;

    @Column (name = "courier_id")
    private String courierId;

    @Column (name = "store_id")
    private String storeId;

    @CreatedDate
    @Column (name = "last_moving_time")
    private LocalDateTime lastMovingTime;

}
