package com.bookingserive.mapper;

import org.springframework.stereotype.Component;

import com.bookingserive.dto.CreateBookingReqDto;
import com.bookingserive.dto.DriverLocationDto;
import com.bookingserive.pojo.CreateBookingReqPojo;
import com.bookingserive.pojo.DriverLocationPojo;

@Component
public class CreateBookingReqMapper {
    
    public CreateBookingReqDto pojoToDto(CreateBookingReqPojo pojo) {
        if (pojo == null) {
            return null;
        }
        
        return CreateBookingReqDto.builder()
                .passengerId(pojo.getPassengerId())
                .startLocation(mapToDto(pojo.getStartLocation()))
                .endLocation(mapToDto(pojo.getEndLocation()))
                .build();
    }
    
    public CreateBookingReqPojo dtoToPojo(CreateBookingReqDto dto) {
        if (dto == null) {
            return null;
        }
        
        return CreateBookingReqPojo.builder()
                .passengerId(dto.getPassengerId())
                .startLocation(mapToPojo(dto.getStartLocation()))
                .endLocation(mapToPojo(dto.getEndLocation()))
                .build();
    }

    private DriverLocationDto mapToDto(DriverLocationPojo pojo) {
        if (pojo == null) {
            return null;
        }
        return DriverLocationDto.builder()
                .latitude(pojo.getLatitude())
                .longitude(pojo.getLongitude())
                .build();
    }

    private DriverLocationPojo mapToPojo(DriverLocationDto dto) {
        if (dto == null) {
            return null;
        }
        return DriverLocationPojo.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
