package com.reservationapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservationappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationappApplication.class, args);
	}

//		config to external library object is loaded in spring ioc, model mapper is external library
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


}
