package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.HouseholdItemsDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.MyBookingsRequest;
import com.mybookingsservice.domain.SelectedItemsDTO;
import com.mybookingsservice.entity.HouseholdItems;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.SelectedItems;


@Mapper(componentModel = "spring", uses = {SelectedItemsMapper.class})
public interface MyBookingsMapper {



	List<SelectedItems> toEntityList(List<SelectedItemsDTO> selectedItems);

	List<MyBookingsRequest> toCustomerBookingDTOs(List<MyBookings> bookings);

	List<HouseholdItemsDTO> toitemsDtoList(List<HouseholdItems> items);

	
	MyBookings toEntity(MyBookingsDTO bookingsDTO);

	MyBookingsDTO toDTO(MyBookings bookingsSaved);
	
	

}
