package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.CustCancelledBookingResponse;
import com.mybookingsservice.domain.CustCancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.HouseholdItemsDTO;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.VendorCancelledBookingDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.entity.HouseholdItems;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.exceptions.StatusHandler;




@Mapper(componentModel = "spring")
public interface CustomerBookingsMapper {

	@Mapping(source = "bookingId", target = "bookingId")
	CustomerBookingResponseDTO toCustomerBookingDTO(MyBookings booking);
	
	@Mapping( source = "bookingId", target = "bookingId")
	public List<CustCancelledBookingResponseDTO> toCustCancelBookingDTOs(List<MyBookings> bookings);
	
	@Mapping(source = "bookingId", target = "bookingId")
	public List<CustomerBookingResponseDTO> toCustomerBookingDTOs(List<MyBookings> bookings);
	
	HouseholdItemsDTO toDto(HouseholdItems entity);
    List<HouseholdItemsDTO> toDtoList(List<HouseholdItems> entities);


    default HouseholdItemsResponse toResponse(List<HouseholdItems> entities, StatusHandler statusHandler) {
        HouseholdItemsResponse response = new HouseholdItemsResponse();
        response.setHouseholdItemsDTO(toDtoList(entities));
        response.setStatusHandler(statusHandler);
        return response;
    }
    
    default CustCancelledBookingResponse toCustCancelBookingMapper(List<MyBookings> entities, StatusHandler statusHandler) {
    	CustCancelledBookingResponse response = new CustCancelledBookingResponse();
        response.setCustCancelledBookingResponse(toCancelDtoList(entities));
        response.setStatusHandler(statusHandler);
        return response;
    }
	
    CustCancelledBookingResponseDTO toDto(MyBookings entity);
    List<CustCancelledBookingResponseDTO> toCancelDtoList(List<MyBookings> entities);
	
}
