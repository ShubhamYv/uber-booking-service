package com.bookingserive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entityservice.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
