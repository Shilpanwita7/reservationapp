package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SubRouteDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.DriverRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

//    @Autowired
//    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    //    ui-> dto-> entity-> repository te save logic-> table a save-> entity->dto-> return dto to controller
//    frontend-> controller-> dto-> service-> entity-> repository-> table
//    @Transactional
    public BusDto addBuss(BusDto busDto) {

//        Bus bus = mapToEntity(busDto);// mapToEntity and mapToDto method ta banate hbe ata run korte hole.
//        driverRepository.save(busDto.getDriver());
//        Bus savedBus = busRepository.save(bus);
//        return mapToDto(savedBus);

        Bus bus = modelMapper.map(busDto, Bus.class);
        Bus savedBus = busRepository.save(bus);
        BusDto dto = modelMapper.map(savedBus, BusDto.class);
        return dto;
    }
}
