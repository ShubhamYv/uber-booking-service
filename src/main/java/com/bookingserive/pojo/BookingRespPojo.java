package com.bookingserive.pojo;

import java.util.Optional;

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
public class BookingRespPojo {

	private long bookingId;
	private String bookingStatus;
	private Optional<Long> driverId;
}
