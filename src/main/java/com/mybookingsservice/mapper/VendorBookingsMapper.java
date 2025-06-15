package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.entity.MyBookings;


@Mapper(componentModel = "spring")
public interface VendorBookingsMapper {

	@Mapping(source = "bookingId", target = "bookingId")
	VendorBookingResponseDTO toVendorBookingDTO(MyBookings booking);
	
	@Mapping(source = "bookingId", target = "bookingId")
	public List<VendorBookingResponseDTO> toVendorBookingDTOs(List<MyBookings> bookings);
	
	@Mapping( source = "bookingId", target = "bookingId")
	public List<VendorCancelledBookingResponse> toVendorCancelBookingDTOs(List<MyBookings> bookings);
}
