package com.reservationapp.payload;


import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusDto {

    private long busId;
    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;

//    private Driver driver;

//    private Route route;
//    private List<SubRoute> subRoutes;
}
