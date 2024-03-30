package com.reservationapp.controller;

import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.service.SubRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/subroute")
public class SubRouteController {

    @Autowired
    private SubRouteService subRouteService;

    @PostMapping("/{routeId}")
    public ResponseEntity<SubRoute> addRoute(@PathVariable long routeId, @RequestBody SubRoute subRoute){
        SubRoute sr= subRouteService.createSubRoute(routeId,subRoute);
        return new ResponseEntity<>(sr, HttpStatus.CREATED);
    }
}
