package com.reservationapp.service;


import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;


    public Route createRoute(long busId, Route route) {

        Bus bus= busRepository.findById(busId).orElseThrow(
                ()->new ResourceNotFound("bus is not there with this bus id first add the bus")
        );

        Route r= routeRepository.findByBusId(route.getBusId());
        if (r!=null){
            throw new ResourceNotFound("route was already added for this busId");
        }
        if (r==null){
            routeRepository.save(route);
            return route;
        }
        return null;
    }
}
