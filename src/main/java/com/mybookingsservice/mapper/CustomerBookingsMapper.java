package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.entity.MyBookings;




@Mapper(componentModel = "spring")
public interface CustomerBookingsMapper {

	@Mapping(source = "bookingId", target = "bookingid")
	CustomerBookingResponseDTO toCustomerBookingDTO(MyBookings booking);
	
	@Mapping(source = "bookingId", target = "bookingid")
	public List<CustomerBookingResponseDTO> toCustomerBookingDTOs(List<MyBookings> bookings);
	
}
