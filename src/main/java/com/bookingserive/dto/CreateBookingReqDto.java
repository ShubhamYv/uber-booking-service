package com.bookingserive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingReqDto {
    private Long passengerId;
    private DriverLocationDto startLocation;
    private DriverLocationDto endLocation;
}
