package com.bookingserive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entityservice.models.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
