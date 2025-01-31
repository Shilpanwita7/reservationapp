package com.reservationapp.entity;


import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;
    private String licenseNumber;
    private String address;
    private  String contactNumber;
    private String alternateContactNumber;
    private String emailId;
}
