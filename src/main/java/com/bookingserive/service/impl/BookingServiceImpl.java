package com.bookingserive.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookingserive.dto.BookingResponseDto;
import com.bookingserive.dto.CreateBookingReqDto;
import com.bookingserive.dto.DriverLocationDto;
import com.bookingserive.dto.NearbyDriversRequestDto;
import com.bookingserive.mapper.ExactLocationMapper;
import com.bookingserive.repositories.BookingRepository;
import com.bookingserive.repositories.PassengerRepository;
import com.bookingserive.service.BookingService;
import com.entityservice.models.Booking;
import com.entityservice.models.BookingStatus;
import com.entityservice.models.ExactLocation;
import com.entityservice.models.Passenger;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final RestTemplate restTemplate;
    private final ExactLocationMapper exactLocationMapper;
	private static final String LOCATION_SERVICE = "http://localhost:8083";

    public BookingServiceImpl(BookingRepository bookingRepository,
                              PassengerRepository passengerRepository, 
                              RestTemplate restTemplate,
                              ExactLocationMapper exactLocationMapper) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.restTemplate = restTemplate;
        this.exactLocationMapper = exactLocationMapper;
    }

    @Override
    public BookingResponseDto createBooking(CreateBookingReqDto bookingDto) {
        Passenger passenger = passengerRepository.findById(bookingDto.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        ExactLocation startLocation = exactLocationMapper.toExactLocation(bookingDto.getStartLocation());
        ExactLocation endLocation = exactLocationMapper.toExactLocation(bookingDto.getEndLocation());

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(startLocation)
                .endLocation(endLocation)
                .passenger(passenger)
                .build();

        Booking newBooking = bookingRepository.save(booking);

        NearbyDriversRequestDto nearbyDriversRequestDto = NearbyDriversRequestDto.builder()
                .latitude(startLocation.getLatitude())
                .longitude(startLocation.getLongitude())
                .build();
        
        ResponseEntity<DriverLocationDto[]> response = restTemplate.postForEntity(
                LOCATION_SERVICE + "/api/v1/location/nearby/drivers",
                nearbyDriversRequestDto,
                DriverLocationDto[].class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            List<DriverLocationDto> driverLocations = Arrays.asList(response.getBody());
            driverLocations.forEach(driver -> 
                System.out.println("Driver ID: " + driver.getDriverId() + 
                                   " | Latitude: " + driver.getLatitude() + 
                                   " | Longitude: " + driver.getLongitude())
            );
        }

        Long driverId = Optional.ofNullable(newBooking.getDriver())
                .map(driver -> driver.getId())
                .orElse(null);

        return BookingResponseDto.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(newBooking.getBookingStatus().toString())
                .driverId(Optional.ofNullable(driverId))
                .build();
    }
}