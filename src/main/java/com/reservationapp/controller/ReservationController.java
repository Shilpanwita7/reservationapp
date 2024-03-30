package com.reservationapp.controller;


import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Passenger;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.payload.ReservationDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.PassengerRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.util.EmailService;
import com.reservationapp.util.ExcelGeneratorService;
import com.reservationapp.util.PdfTicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private PdfTicketGeneratorService pdfTicketGeneratorService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExcelGeneratorService excelGeneratorService;

//    http://localhost:8080/api/v1/reservation?busId=1&routeId=
    @PostMapping
    public ResponseEntity<String> bookTicket(
            @RequestParam long busId,
            @RequestParam long routeId,
            @RequestBody Passenger passenger
            ){

        boolean busIsPresent=false;
        boolean routeIsPresent=false;
        boolean subRouteIsPresent=false;

        Optional<Bus> byId=busRepository.findById(busId);
        if (byId.isPresent()){
            busIsPresent=true;
            Bus bus=byId.get();
        }

        Optional<Route> byRouteId=routeRepository.findById(routeId);
        if (byRouteId.isPresent()){
            subRouteIsPresent=true;
            Bus bus=byId.get();
        }

        Optional<SubRoute> bySubRouteId=subRouteRepository.findById(routeId);
        if (byRouteId.isPresent()){
            routeIsPresent=true;
            Bus bus=byId.get();
        }

      if (busIsPresent&&routeIsPresent || busIsPresent&&subRouteIsPresent){
         Passenger p=new Passenger();
         p.setFirstName(passenger.getFirstName());
         p.setLastName(passenger.getLastName());
         p.setEmail(passenger.getEmail());
         p.setMobile(passenger.getMobile());
         p.setRouteId(routeId);
         p.setBusId(busId);

          Passenger savedPassenger = passengerRepository.save(p);
          byte[] pdfBytes = pdfTicketGeneratorService.generatePdfTicket(savedPassenger, byRouteId.get().getFromLocation(),
                  byRouteId.get().getToLocation(), byRouteId.get().getFromDate(),byId.get().getBusNumber(),
                  byId.get().getBusType(), byRouteId.get().getTotalDuration());
          emailService.sendEmailWithAttachment(passenger.getEmail(),"Booking Confirm","Your reservation id"+savedPassenger.getId(),
                  pdfBytes,"Ticket");

      }
        return new ResponseEntity<>("done", HttpStatus.CREATED);
    }

    @GetMapping("/passengers/excel")
    public ResponseEntity<byte[]> generateExcel() {
        try {
            List<Passenger> passengers = fetchPassengersFromDatabase(); // Fetch persons from database
            byte[] excelBytes = excelGeneratorService.generateExcel(passengers);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "passenger_data.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Dummy method to fetch passengers (replace with actual data retrieval logic)
        private List<Passenger> fetchPassengersFromDatabase(){
            return passengerRepository.findAll();
        }
    }


