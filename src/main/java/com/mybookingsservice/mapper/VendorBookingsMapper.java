package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.domain.VendorDTO;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.StatusHandler;


@Mapper(componentModel = "spring")
public interface VendorBookingsMapper {
	
	
	VendorBookingsMapper INSTANCE = Mappers.getMapper(VendorBookingsMapper.class);

    VendorDTO toDTO(Vendor vendor);
    List<VendorDTO> toDTOs(List<Vendor> vendors);
	

	@Mapping(source = "bookingId", target = "bookingId")
	VendorBookingResponseDTO toVendorBookingDTO(MyBookings booking);
	
	@Mapping(source = "bookingId", target = "bookingId")
	public List<VendorBookingResponseDTO> toVendorBookingDTOs(List<MyBookings> bookings);
	
	@Mapping( source = "bookingId", target = "bookingId")
	public List<VendorCancelledBookingDTO> toVendorCancelBookingDTOs(List<MyBookings> bookings);
	
	default VendorCancelledBookingResponse toVendorCancelBookingMapper(List<MyBookings> entities, StatusHandler statusHandler) {
		VendorCancelledBookingResponse response = new VendorCancelledBookingResponse();
        response.setVendorCancelledBookingDTO(toDtoList(entities));
        response.setStatusHandler(statusHandler);
        return response;
    }
	
	VendorCancelledBookingDTO toDto(MyBookings entity);
    List<VendorCancelledBookingDTO> toDtoList(List<MyBookings> entities);
    
    
    
	
}
