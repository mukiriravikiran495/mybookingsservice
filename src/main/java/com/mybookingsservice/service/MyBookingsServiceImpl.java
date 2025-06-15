package com.mybookingsservice.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.CancelResponseDTO;
import com.mybookingsservice.domain.CancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.mapper.CustomerBookingsMapper;
import com.mybookingsservice.mapper.MyBookingsMapper;
import com.mybookingsservice.repository.CustomerBookingsRepository;
import com.mybookingsservice.repository.MyBookingsRepository;

@Service("MyBookingsService")
public class MyBookingsServiceImpl implements MyBookingsService{

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	MyBookingsRepository repository;
	
	@Autowired
	CustomerBookingsRepository custRepository;
	
	@Autowired
	MyBookingsMapper mapper;
	
	@Autowired
	CustomerBookingsMapper customerMapper;
	
	
	
	@Override
	public List<MyBookingsDTO> getall() {
		
		List<MyBookings> list = repository.findAllWithCustomerAndVendor();

			return mapper.toDtoList(list);
		
	}
	

	@Override
	public CustomerBookingResponseDTO getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustomerBookingResponseDTO customerBookingResponse) {
		logger.info("START : Get Bookings By ID Service : "+custId+" "+bookingId);
		MyBookings bookings = custRepository.findByBookingAndCustomer(custId, bookingId);
		
		logger.info("END : Get Bookings By ID Service : "+custId+" "+bookingId);
		return customerMapper.toCustomerBookingDTO(bookings);
	}

	@Override
	public CancelResponseDTO cancelBookingById(Long custId, Long bookingId, CancelResponseDTO cancelResponse,
			StatusHandler statusHandler) {
		logger.info("Start : Cancel Booking by Id Service : "+custId+" "+bookingId);
		int updated = custRepository.updateBookingStatus(custId, bookingId, "Cancelled");
		if( updated > 0 ) {
			cancelResponse.setCustId(custId);
			cancelResponse.setBookingId(bookingId);
			cancelResponse.setStatus(AppConstants.Cancelled_Succesfully);
			statusHandler.setStatusCode("200");
			statusHandler.setMessage("Booking ID : "+bookingId+" is Cancelled Succesfully : ");
			cancelResponse.setStatusHandler(statusHandler);
		}else {
			cancelResponse.setCustId(custId);
			cancelResponse.setBookingId(bookingId);
			cancelResponse.setStatus(AppConstants.CANCEL_FAILED);
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage("Booking ID : "+bookingId+" Not Found : ");
			cancelResponse.setStatusHandler(statusHandler);
		}
		return cancelResponse;
	}

	public List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId) {
		logger.info("Start : get all bookings service fro custId : "+custId);
        List<MyBookings> bookings = custRepository.findByCustomerDetails_CustId(custId);
        logger.info("End : get all bookings service fro custId : "+custId);
        return customerMapper.toCustomerBookingDTOs(bookings);
    }

	@Override
	public List<CancelledBookingResponseDTO> getAllCancelledBookings(Long custId, String status,
			CancelledBookingResponseDTO cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : get all cancelled bookings service for custId : "+custId);
		List<MyBookings> bookings = custRepository.findByBookingAndCustomerAndStatus(custId, status);
		
		logger.info("END : get all cancelled bookings service for custId : "+custId);
		return customerMapper.toCancelBookingDTOs(bookings);
	}


}
