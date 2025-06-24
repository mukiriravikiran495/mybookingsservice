package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.HouseholdItemsDTO;
import com.mybookingsservice.domain.MyBookingsRequestDTO;
import com.mybookingsservice.domain.SelectedItemsDTO;
import com.mybookingsservice.entity.HouseholdItems;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.SelectedItems;


@Mapper(componentModel = "spring", uses = {SelectedItemsMapper.class})
public interface MyBookingsMapper {

	
//	@Mapping(target = "customerDetails.bookings", ignore = true) // Add this
//    @Mapping(target = "vendorDetails.bookings", ignore = true)   // Add this
	@Mapping(source = "selectedItems", target = "selectedItems")
	MyBookingsRequestDTO toDto(MyBookings booking);

	List<SelectedItems> toEntityList(List<SelectedItemsDTO> selectedItems);

	List<MyBookingsRequestDTO> toCustomerBookingDTOs(List<MyBookings> bookings);

	List<HouseholdItemsDTO> toitemsDtoList(List<HouseholdItems> items);
	
	

}
