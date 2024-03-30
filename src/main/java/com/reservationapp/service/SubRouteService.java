package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubRouteService {

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private RouteRepository routeRepository;

    public SubRoute createSubRoute(long routeId, SubRoute subRoute) {

        Route route= routeRepository.findById(routeId).orElseThrow(
                ()->new ResourceNotFound("route is not there with this route id first add the route")
        );

        SubRoute sr= subRouteRepository.findByRouteId(subRoute.getRouteId());
        if (sr!=null){
            throw new ResourceNotFound("subroute was already added for this routeId");
        }
        if (sr==null){
            subRouteRepository.save(subRoute);
            return subRoute;
        }
        return null;
    }
}
