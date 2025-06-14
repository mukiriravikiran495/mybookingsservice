package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mybookingsservice.domain.CustAddressDTO;
import com.mybookingsservice.domain.CustomerDetailsDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.VendorAddressDTO;
import com.mybookingsservice.domain.VendorDetailsDTO;
import com.mybookingsservice.entity.CustAddress;
import com.mybookingsservice.entity.CustomerDetails;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.VendorAddress;
import com.mybookingsservice.entity.VendorDetails;

@Mapper(componentModel = "spring")
public interface MyBookingsMapper {

	
	@Mapping(target = "customerDetails.bookings", ignore = true) // Add this
    @Mapping(target = "vendorDetails.bookings", ignore = true)   // Add this
	MyBookingsDTO toDto(MyBookings booking);

	List<MyBookingsDTO> toDtoList(List<MyBookings> bookings);

	@Mapping(target = "custAddress", source = "custAddress")
	CustomerDetailsDTO toDto(CustomerDetails entity);

	@Mapping(target = "vendorAddress", source = "vendorAddress")
	VendorDetailsDTO toDto(VendorDetails entity);

	@Mapping(source = "c_address_id", target = "c_address_Id")
	CustAddressDTO toCustAddressDTO(CustAddress custAddress);

	List<CustAddressDTO> toCustAddressDTOList(List<CustAddress> addresses);

	@Mapping(source = "v_address_id", target = "v_address_Id")
	VendorAddressDTO toVendorAddressDTO(VendorAddress vendorAddress);

	List<VendorAddressDTO> toVendorAddressDTOList(List<VendorAddress> addresses);

}
