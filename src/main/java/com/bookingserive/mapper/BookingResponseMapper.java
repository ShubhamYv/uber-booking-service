package com.bookingserive.mapper;

import org.springframework.stereotype.Component;

import com.bookingserive.dto.BookingResponseDto;
import com.bookingserive.pojo.BookingRespPojo;

@Component
public class BookingResponseMapper {
    
    public BookingResponseDto pojoToDto(BookingRespPojo pojo) {
        if (pojo == null) {
            return null;
        }
        
        return BookingResponseDto.builder()
                .bookingId(pojo.getBookingId())
                .bookingStatus(pojo.getBookingStatus())
                .driverId(pojo.getDriverId())
                .build();
    }
    
    public BookingRespPojo dtoToPojo(BookingResponseDto dto) {
        if (dto == null) {
            return null;
        }
        
        return BookingRespPojo.builder()
                .bookingId(dto.getBookingId())
                .bookingStatus(dto.getBookingStatus())
                .driverId(dto.getDriverId())
                .build();
    }
}
