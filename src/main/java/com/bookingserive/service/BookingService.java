package com.bookingserive.service;

import com.bookingserive.dto.BookingResponseDto;
import com.bookingserive.dto.CreateBookingReqDto;

public interface BookingService {

	public BookingResponseDto createBooking(CreateBookingReqDto bookingDto);
}
