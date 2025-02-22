package com.bookingserive.mapper;

import org.springframework.stereotype.Component;

import com.bookingserive.dto.DriverLocationDto;
import com.entityservice.models.ExactLocation;

@Component
public class ExactLocationMapper {
    
    public ExactLocation toExactLocation(DriverLocationDto dto) {
        if (dto == null) {
            return null;
        }
        return ExactLocation.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }

    public DriverLocationDto toDriverLocationDto(ExactLocation exactLocation) {
        if (exactLocation == null) {
            return null;
        }
        return DriverLocationDto.builder()
                .latitude(exactLocation.getLatitude())
                .longitude(exactLocation.getLongitude())
                .build();
    }
}
