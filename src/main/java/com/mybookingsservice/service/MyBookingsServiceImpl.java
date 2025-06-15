package com.mybookingsservice.service;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.CustCancelResponseDTO;
import com.mybookingsservice.domain.CustCancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.VendorDetails;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.mapper.CustomerBookingsMapper;
import com.mybookingsservice.mapper.MyBookingsMapper;
import com.mybookingsservice.mapper.VendorBookingsMapper;
import com.mybookingsservice.repository.MyBookingsRepository;

@Service("MyBookingsService")
public class MyBookingsServiceImpl implements MyBookingsService{

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	MyBookingsRepository repository;
	
	@Autowired
	MyBookingsMapper mapper;
	
	@Autowired
	CustomerBookingsMapper customerMapper;
	
	@Autowired
	VendorBookingsMapper vendorMapper;
	
	
	
	@Override
	public List<MyBookingsDTO> getall() {
		
		List<MyBookings> list = repository.findAllWithCustomerAndVendor();

			return mapper.toDtoList(list);
		
	}
	

	@Override
	public CustomerBookingResponseDTO getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustomerBookingResponseDTO customerBookingResponse) {
		logger.info("START : Get Bookings By ID Service : "+custId+" "+bookingId);
		MyBookings bookings = repository.findByBookingAndCustomer(custId, bookingId);
		
		logger.info("END : Get Bookings By ID Service : "+custId+" "+bookingId);
		return customerMapper.toCustomerBookingDTO(bookings);
	}

	@Override
	public CustomerBookingResponseDTO cancelBookingById(Long custId, Long bookingId, CustomerBookingResponseDTO cancelResponse, StatusHandler statusHandler) {
		logger.info("Start : Cancel Booking by Id Service : "+custId+" "+bookingId);
		
		MyBookings bookings = repository.findByBookingAndCustomer(custId, bookingId);
		bookings.setStatus(AppConstants.CANCELLED);
		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
		
		return customerMapper.toCustomerBookingDTO(bookings);
	}

	public List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId) {
		logger.info("Start : get all bookings service fro custId : "+custId);
        List<MyBookings> bookings = repository.findByCustomerDetails_CustId(custId);
        logger.info("End : get all bookings service fro custId : "+custId);
        return customerMapper.toCustomerBookingDTOs(bookings);
    }

	@Override
	public List<CustCancelledBookingResponseDTO> getAllCancelledBookings(Long custId, String status,
			CustCancelledBookingResponseDTO cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : get all cancelled bookings service for custId : "+custId);
		List<MyBookings> bookings = repository.findByBookingAndCustomerAndStatus(custId, status);
		
		logger.info("END : get all cancelled bookings service for custId : "+custId);
		return customerMapper.toCustCancelBookingDTOs(bookings);
	}

	@Override
	public List<VendorBookingResponseDTO> getBookingByVendorId(long vendorId) {
		logger.info("START : VendorBookings Service ");
		List<MyBookings> bookings = repository.findByVendorDetails_VendorId(vendorId);
		
		logger.info("END : VendorBookings Service : ");
	    return vendorMapper.toVendorBookingDTOs(bookings);
	}


	@Override
	public VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler) {
		logger.info("Start : get bookings by vendorId and bookingId service : "+vendorId+" "+bookingId);
		MyBookings bookings = repository.findByBookingAndVendor(vendorId, bookingId);
		
		logger.info("End : get bookings by vendorId and bookingId service : "+vendorId+" "+bookingId);
		return vendorMapper.toVendorBookingDTO(bookings);
	}

	
	@Override
	public VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, 
			VendorBookingResponseDTO vendorResponse, StatusHandler statusHandler) {
		logger.info("Start : Cancel Bookings by Vendor Service : "+vendorId+" "+bookingId);
		
		MyBookings bookings = repository.findByBookingAndVendor(vendorId, bookingId);
		bookings.setStatus(AppConstants.CANCELLED);
		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
		logger.info("End : Cancel Bookings by Vendor Service : "+vendorId+" "+bookingId);
		return vendorMapper.toVendorBookingDTO(bookings);
	}


	@Override
	public List<VendorCancelledBookingResponse> getVendorCancelledBookings(Long vendorId, String status,
			VendorCancelledBookingResponse cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : Get all Vendor Cancelled Bookings : "+vendorId);
		List<MyBookings> bookings = repository.findByBookingAndVendorAndStatus(vendorId, status);
		
		logger.info("End : Get all Vendor Cancelled Bookings : "+vendorId);
		return vendorMapper.toVendorCancelBookingDTOs(bookings);
	}


	@Override
	public VendorBookingResponseDTO acceptBpooking(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorBookings, StatusHandler statusHandler) {
		logger.info("Start : Accept Bookings Service : "+vendorId+" "+bookingId);
		
		MyBookings bookings = repository.findByBookingAndVendor(vendorId, bookingId);
		bookings.setStatus(AppConstants.ACCEPTED);
		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
		logger.info("End : Accept Bookings Service : "+vendorId+" "+bookingId);
		return vendorMapper.toVendorBookingDTO(bookings);
	}


}
