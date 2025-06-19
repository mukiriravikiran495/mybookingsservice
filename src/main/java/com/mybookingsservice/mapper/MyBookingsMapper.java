package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.CustAddressDTO;
import com.mybookingsservice.domain.CustomerDetailsDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.MyBookingsResponseDTO;
import com.mybookingsservice.domain.SelectedItemsDTO;
import com.mybookingsservice.domain.VendorAddressDTO;
import com.mybookingsservice.domain.VendorDetailsDTO;
import com.mybookingsservice.entity.CustAddress;
import com.mybookingsservice.entity.CustomerDetails;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.SelectedItems;
import com.mybookingsservice.entity.VendorAddress;
import com.mybookingsservice.entity.VendorDetails;

@Mapper(componentModel = "spring")
public interface MyBookingsMapper {

	
//	@Mapping(target = "customerDetails.bookings", ignore = true) // Add this
//    @Mapping(target = "vendorDetails.bookings", ignore = true)   // Add this
	MyBookingsDTO toDto(MyBookings booking);

	List<MyBookingsDTO> toDtoList(List<MyBookings> bookings);

	@Mapping(target = "custAddress", source = "custAddress")
	CustomerDetailsDTO toDto(CustomerDetails entity);

	@Mapping(target = "vendorAddress", source = "vendorAddress")
	VendorDetailsDTO toDto(VendorDetails entity);
	
	@Mapping(source = "itemId", target = "itemId")
	SelectedItemsDTO toDto(SelectedItems entity);

	@Mapping(source = "cAddressId", target = "cAddressId")
	CustAddressDTO toCustAddressDTO(CustAddress custAddress);

	List<CustAddressDTO> toCustAddressDTOList(List<CustAddress> addresses);

	@Mapping(source = "vAddressId", target = "vAddressId")
	VendorAddressDTO toVendorAddressDTO(VendorAddress vendorAddress);

	List<VendorAddressDTO> toVendorAddressDTOList(List<VendorAddressDTO> addresses);
	
	SelectedItems toEntity(SelectedItemsDTO dto);


    List<SelectedItems> toEntityList(List<SelectedItemsDTO> dtoList);

    List<SelectedItemsDTO> toDtoSelectedList(List<SelectedItems> entityList);

	MyBookings toEntity(MyBookingsDTO mybookingsDTO);

//	MyBookingsResponseDTO toBookingsDto(MyBookings bookings);
//
//	MyBookingsResponseDTO toResponseDTO(MyBookings b);
	
	List<CustAddress> toAddressEntityList(List<CustAddressDTO> dtoList);

}
