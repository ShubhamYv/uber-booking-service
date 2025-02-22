package com.bookingserive.pojo;

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
public class CreateBookingReqPojo {
    private Long passengerId;
    private DriverLocationPojo startLocation;
    private DriverLocationPojo endLocation;
}
