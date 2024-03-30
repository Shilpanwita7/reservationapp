package com.reservationapp.entity;


import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private long busId;
    private long routeId;
}
