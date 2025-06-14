package com.mybookingsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.mapper.CustomerBookingsMapper;
import com.mybookingsservice.mapper.MyBookingsMapper;
import com.mybookingsservice.repository.CustomerBookingsRepository;
import com.mybookingsservice.repository.MyBookingsRepository;

@Service("MyBookingsService")
public class MyBookingsServiceImpl implements MyBookingsService{

	private final MyBookingsRepository repository;
	
	@Autowired
	CustomerBookingsRepository custRepository;
	
	
	private final MyBookingsMapper mapper;
	
	@Autowired
	CustomerBookingsMapper customerMapper;
	
	@Autowired
	public MyBookingsServiceImpl(MyBookingsRepository repository, MyBookingsMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public List<MyBookingsDTO> getall() {
		
		List<MyBookings> list = repository.findAllWithCustomerAndVendor();

			return mapper.toDtoList(list);
		
	}
	
	public List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId) {
        List<MyBookings> bookings = custRepository.findByCustomerDetails_CustId(custId);
        return customerMapper.toCustomerBookingDTOs(bookings);
    }

}
