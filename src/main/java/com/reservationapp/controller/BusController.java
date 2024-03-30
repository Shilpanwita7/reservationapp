package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SearchListOfBusesDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

//    http://localhost:8080/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<BusDto> addBus(@RequestBody BusDto busDto) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
//
//        Date fromDate= dateFormat.parse(busDto.getFromDate());
//        Date toDate= dateFormat.parse(busDto.getToDate());

        BusDto dto = busService.addBuss(busDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }


//        http://localhost:8080/api/v1/bus?fromLocation=&toLocation=&fromDate
        @GetMapping
        public List<SearchListOfBusesDto> getAllBuses(@RequestParam(required = true) String fromLocation,
                                     @RequestParam(required = true) String toLocation,
                                     @RequestParam(required = true) String fromDate){
           List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
           List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);

           List<SearchListOfBusesDto> buses=new ArrayList<>();

           if (routes!=null) {
               for (Route route : routes) {
                   Bus bus = busRepository.findById(route.getBusId()).get();
                   SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                   buses.add(searchListOfBusesDto);
               }
               return buses;
           }

            if (subRoutes!=null) {
                for (SubRoute subRoute : subRoutes) {
                    Bus bus = busRepository.findById(subRoute.getBusId()).get();
                    SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, subRoute);
                    buses.add(searchListOfBusesDto);
                }
                return buses;
            }
            return null;
        }

        SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, Route route){
        SearchListOfBusesDto searchListOfBusesDto= new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());

        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());
        searchListOfBusesDto.setRouteId(route.getId());

        return searchListOfBusesDto;
        }

    SearchListOfBusesDto mapToSearchListOfBusesDto(Bus bus, SubRoute subRoute){
        SearchListOfBusesDto searchListOfBusesDto= new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());

        searchListOfBusesDto.setFromLocation(subRoute.getFromLocation());
        searchListOfBusesDto.setToLocation(subRoute.getToLocation());
        searchListOfBusesDto.setFromDate(subRoute.getFromDate());
        searchListOfBusesDto.setToDate(subRoute.getToDate());
        searchListOfBusesDto.setTotalDuration(subRoute.getTotalDuration());
        searchListOfBusesDto.setFromTime(subRoute.getFromTime());
        searchListOfBusesDto.setToTime(subRoute.getToTime());
        searchListOfBusesDto.setRouteId(subRoute.getId());

        return searchListOfBusesDto;
    }
    }

