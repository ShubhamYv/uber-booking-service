package com.bookingserive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingserive.pojo.CreateBookingReqPojo;
import com.bookingserive.service.BookingService;
import com.bookingserive.dto.BookingResponseDto;
import com.bookingserive.dto.CreateBookingReqDto;
import com.bookingserive.mapper.BookingResponseMapper;
import com.bookingserive.mapper.CreateBookingReqMapper;
import com.bookingserive.pojo.BookingRespPojo;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

	private final BookingService bookingService;
	private final CreateBookingReqMapper bookingReqMapper;
	private final BookingResponseMapper bookingResponseMapper;

	public BookingController(BookingService bookingService, CreateBookingReqMapper bookingReqMapper,
			BookingResponseMapper bookingResponseMapper) {
		this.bookingService = bookingService;
		this.bookingReqMapper = bookingReqMapper;
		this.bookingResponseMapper = bookingResponseMapper;
	}

	@PostMapping()
	public ResponseEntity<BookingRespPojo> createBooking(@RequestBody CreateBookingReqPojo bookingReqPojo) {
		CreateBookingReqDto bookingReqDto = bookingReqMapper.pojoToDto(bookingReqPojo);
		BookingResponseDto bookingRespDto = bookingService.createBooking(bookingReqDto);
		BookingRespPojo bookingRespPojo = bookingResponseMapper.dtoToPojo(bookingRespDto);
		return new ResponseEntity<>(bookingRespPojo, HttpStatus.CREATED);
	}
}
