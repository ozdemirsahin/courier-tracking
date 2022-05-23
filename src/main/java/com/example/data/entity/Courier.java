package com.example.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "courier")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Courier {

    @Id
    @Column(name = "id")
    private String id;

    @Column (name = "personal_no")
    private String personalNo;

    @Column (name = "lat")
    private Double lat;

    @Column (name = "lng")
    private Double lng;

    @Column (name = "total_distance")
    private Double totalDistance;

}
