package com.reservationapp.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @Column(name = "bus_number", unique = true)
    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;

//    @OneToOne
//    @JoinColumn(name="driver_id")//unidirectional mapping
//    private Driver driver;//here main table is bus, driver is sub table

//    @OneToOne(mappedBy = "bus")//bidirectional mapping
//    private Route route;// here main table is route, bus is sub table


}
