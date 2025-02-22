package com.bookingserive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entityservice.models.ExactLocation;

public interface LocationRepository extends JpaRepository<ExactLocation, Long> {

}
