package com.mybookingsservice.service;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybookingsservice.config.SecurityConfig;
import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.BookingTypeRequest;
import com.mybookingsservice.domain.CustCancelledBookingResponse;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.HouseholdItemsDTO;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.MyBookingsResponseDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.domain.VendorDTO;
import com.mybookingsservice.domain.VendorEstimateRequest;
import com.mybookingsservice.domain.VendorEstimateResponse;
import com.mybookingsservice.domain.VendorServiceAreaDTO;
import com.mybookingsservice.entity.HouseholdItems;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.mapper.CustomerBookingsMapper;
import com.mybookingsservice.mapper.MyBookingsMapper;
import com.mybookingsservice.mapper.VendorBookingsMapper;
import com.mybookingsservice.repository.CustomerRepository;
import com.mybookingsservice.repository.HouseholdRepository;
import com.mybookingsservice.repository.MyBookingsRepository;
import com.mybookingsservice.repository.VendorDetailsRepository;
import com.mybookingsservice.repository.VendorRepository;

@Service("MyBookingsService")
public class MyBookingsServiceImpl implements MyBookingsService{

    private final SecurityConfig securityConfig;

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	MyBookingsRepository repository;
	
	@Autowired
	HouseholdRepository houseRepository;
	
	@Autowired
	MyBookingsMapper mapper;
	
	@Autowired
	CustomerBookingsMapper customerMapper;
	
	@Autowired
	VendorBookingsMapper vendorMapper;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	VendorDetailsRepository vendorDetailsRepository;

    MyBookingsServiceImpl(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }
	
	@Override
	public List<MyBookingsDTO> getall() {
		
//		List<MyBookings> list = repository.findAllWithCustomerAndVendor();
//
//			return mapper.toDtoList(list);
		return null;
	}
	

	@Override
	public CustomerBookingResponseDTO getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustomerBookingResponseDTO customerBookingResponse) {
//		logger.info("START : Get Bookings By ID Service : "+custId+" "+bookingId);
//		MyBookings bookings = repository.findByBookingIdAndCustId(custId, bookingId);
//		
//		logger.info("END : Get Bookings By ID Service : "+custId+" "+bookingId);
//		return customerMapper.toCustomerBookingDTO(bookings);
		return null;
	}

	@Override
	public CustomerBookingResponseDTO cancelBookingById(Long custId, Long bookingId, CustomerBookingResponseDTO cancelResponse, StatusHandler statusHandler) {
//		logger.info("Start : Cancel Booking by Id Service : "+custId+" "+bookingId);
//		
//		MyBookings bookings = repository.findByBookingIdAndCustId(custId, bookingId);
//		try {
//			if( null == bookings) {
//				throw new InvalidRequestException(AppConstants.NOT_FOUND);
//			}
//		}catch(InvalidRequestException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(AppConstants.NOT_FOUND);
//			cancelResponse.setStatusHandler(statusHandler);
//			cancelResponse.setStatus(AppConstants.CANCEL_FAILED);
//			return cancelResponse;
//		}
//		
//		bookings.setStatus(AppConstants.CANCELLED);
//		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
//		
//		return customerMapper.toCustomerBookingDTO(bookings);
		return null;
	}

	public List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId) {
//		logger.info("Start : get all bookings service fro custId : "+custId);
//        List<MyBookings> bookings = repository.findByCustId(custId);
//        
//        
//        logger.info("End : get all bookings service fro custId : "+custId);
//        return customerMapper.toCustomerBookingDTOs(bookings);
		return null;
    }

	@Override
	public CustCancelledBookingResponse getAllCancelledBookings(Long custId, String status,
			CustCancelledBookingResponse cancelledBookings, StatusHandler statusHandler) {
//		logger.info("Start : get all cancelled bookings service for custId : "+custId);
//		List<MyBookings> bookings = repository.findByCustIdAndStatus(custId, status);
//		
//		logger.info("END : get all cancelled bookings service for custId : "+custId);
//		return customerMapper.toCustCancelBookingMapper(bookings, statusHandler);
		return null;
	}

	@Override
	public List<VendorBookingResponseDTO> getBookingByVendorId(long vendorId) {
//		logger.info("START : VendorBookings Service ");
//		List<MyBookings> bookings = repository.findByVendorId(vendorId);
//		
//		logger.info("END : VendorBookings Service : ");
//	    return vendorMapper.toVendorBookingDTOs(bookings);
		return null;
	}


	@Override
	public VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler) {
//		logger.info("Start : get bookings by vendorId and bookingId service : "+vendorId+" "+bookingId);
//		MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//		
//		logger.info("End : get bookings by vendorId and bookingId service : "+vendorId+" "+bookingId);
//		return vendorMapper.toVendorBookingDTO(bookings);
		return null;
	}

	
	@Override
	public VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, 
			VendorBookingResponseDTO vendorResponse, StatusHandler statusHandler) {
//		logger.info("Start : Cancel Bookings by Vendor Service : "+vendorId+" "+bookingId);
//		
//		MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//		bookings.setStatus(AppConstants.CANCELLED);
//		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
//		logger.info("End : Cancel Bookings by Vendor Service : "+vendorId+" "+bookingId);
//		return vendorMapper.toVendorBookingDTO(bookings);
		return null;
	}


	@Override
	public VendorCancelledBookingResponse getVendorCancelledBookings(Long vendorId, String status,
			VendorCancelledBookingResponse cancelledBookings, StatusHandler statusHandler) {
//		logger.info("Start : Get all Vendor Cancelled Bookings : "+vendorId);
//		List<MyBookings> bookings = repository.findByVendorIdAndStatus(vendorId, status);
//		
//		logger.info("End : Get all Vendor Cancelled Bookings : "+vendorId);
//		return vendorMapper.toVendorCancelBookingMapper(bookings, statusHandler);
		return null;
	}


	@Override
	public VendorBookingResponseDTO acceptBpooking(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorBookings, StatusHandler statusHandler) {
//		logger.info("Start : Accept Bookings Service : "+vendorId+" "+bookingId);
//		
//		MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//		bookings.setStatus(AppConstants.ACCEPTED);
//		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
//		logger.info("End : Accept Bookings Service : "+vendorId+" "+bookingId);
//		return vendorMapper.toVendorBookingDTO(bookings);
		return null;
	}


	@Override
	public HouseholdItemsResponse  getHouseHoldItems(String estCategory, HouseholdItemsResponse itemResponse,
			StatusHandler statusHandler) {
//		logger.info("Start : Get Household Items : "+estCategory);
//		
//		List<String> categories = Arrays.asList("ONEBHK", "None");
//        
//		
//		List<HouseholdItems> items =  houseRepository.findByEstCategoryIn(Arrays.asList("ONEBHK", "None"));
//		System.out.println(items.toString());
//		
//		logger.info("END : Get Household Items : "+estCategory);
//		return customerMapper.toResponse(items, statusHandler);
		return null;
	}


	@Override
	public Vendor getvendorProfile(long vendorId) {
//		Vendor vendor = vendorRepository.findByVendorId(vendorId);
//		return vendor;
		return null;
	}


	@Override
	public VendorEstimateResponse getvendorEstimates(VendorEstimateRequest request,
			VendorEstimateResponse vendorEstimatesResponse, StatusHandler statusHandler) {
		logger.info("Start : Get Estimates Vendors : service : " + request);

		List<VendorNativeResult> flatResult = vendorRepository.findVendorsByZipcodeNative(request.getPickup_zipcode());

		Map<Long, VendorDTO> vendorMap = new LinkedHashMap<>();

		for (VendorNativeResult row : flatResult) {
			VendorDTO vendorDTO = vendorMap.computeIfAbsent(row.getVendorId(), id -> {
				VendorDTO dto = new VendorDTO();
				dto.setVendorId(row.getVendorId());
				dto.setvFirstname(row.getVfirstname());
				dto.setvLastname(row.getVlastname());
				dto.setvMobile(row.getVmobile());
				dto.setvEmail(row.getVemail());
				dto.setVendorServiceAreaDTO(new ArrayList<>());
				return dto;
			});

			VendorServiceAreaDTO areaDTO = new VendorServiceAreaDTO();
			areaDTO.setvServiceId(row.getVserviceId());
			areaDTO.setvZipcode(row.getVzipcode());
			areaDTO.setBasePricePerKm(row.getBasePricePerKm());
			areaDTO.setPricePerKg(row.getPricePerKg());
			areaDTO.setAvgDeliveryTimeInDays(row.getAvgDeliveryTimeInDays());
			int weight = 0;
			for (int i = 0; i < request.getSelectedItems().size(); i++) {
				weight = weight + request.getSelectedItems().get(i).getWeight();
			}
			int estimatedPrice = weight * row.getBasePricePerKm() + row.getPricePerKg() * 10;
			areaDTO.setEstimatedPrice(estimatedPrice);

			areaDTO.setEstimatedDeliveryDate(null);
			vendorDTO.getVendorServiceAreaDTO().add(areaDTO);
		}

		VendorEstimateResponse response = new VendorEstimateResponse();
		response.setBOOKING_DATE(LocalDateTime.now());
		response.setSCHEDULED_DATE(request.getScheduled_date());
		response.setSERVICE_TYPE(request.getService_type());

		response.setPickupAddress(request.getPickup_address());
		response.setPickupLatitude(request.getPickup_latitude());
		response.setPickupLongitude(request.getPickup_longitude());
		response.setPickup_zipcode(request.getPickup_zipcode());
		response.setDrop_zipcode(request.getDrop_zipcode());
		response.setDropAddress(request.getDrop_address());
		response.setDropLatitude(request.getDrop_latitude());
		response.setDropLongitude(request.getDrop_longitude());

		response.setVendorDTO(new ArrayList<>(vendorMap.values()));

		StatusHandler status = new StatusHandler();
		status.setStatusCode("200");
		status.setMessage("Estimate success");
		response.setStatusHandler(status);

		return response;

	}


	@Override
	@Transactional
	public MyBookingsResponseDTO createBookings(MyBookingsDTO mybookingsDTO, MyBookingsResponseDTO myBookingsResponse,
			StatusHandler statusHandler) {
		logger.info("Start : Create Bookings Service : "+mybookingsDTO);
//		MyBookings bookings = new MyBookings();
//		bookings.setStatus("CONFIRM");
//		
//		List<SelectedItems> list = new ArrayList<>();
//		for( SelectedItemsDTO selected : mybookingsDTO.getSelectedItems()) {
//			SelectedItems selectedItems = new SelectedItems();
//			selectedItems.setItemCode(selected.getItemCode());
//			selectedItems.setItemName(selected.getItemName());
//			selectedItems.setCategory(selected.getCategory());
//			selectedItems.setEstCategory(selected.getEstCategory());
//			selectedItems.setWeight(selected.getWeight());
//			selectedItems.setQty(selected.getQty());
//			selectedItems.setCreatedBy(selected.getCreatedBy());
//			selectedItems.setUpdatedBy(selected.getUpdatedBy());
//			selectedItems.setIsActive(selected.getIsActive());
//			selectedItems.setBooking(bookings);
//			list.add(selectedItems);
//		}
//		bookings.setSelectedItems(list);
//		
//		CustomerDetails custDetails = customerRepository.findById(mybookingsDTO.getCustomerDetails().getCustId() )
//				.orElseThrow(() -> new RuntimeException("CustId not found : " +mybookingsDTO.getCustomerDetails().getCustId() ));
//		
//		
//		CustomerDetails cust = new CustomerDetails();
//		cust.setCustId(custDetails.getCustId());
//		cust.setC_firstName(custDetails.getC_firstName());
//		cust.setC_lastName(custDetails.getC_lastName());
//		cust.setC_mobile(custDetails.getC_mobile());
//		cust.setC_email(custDetails.getC_email());
//		
//		CustAddressDTO addressDTO = mybookingsDTO.getCustomerDetails().getCustAddress();
//		CustAddress address = new CustAddress();
//		address.setC_address1(addressDTO.getC_address1());
////		address.setC_address_id(addressDTO.getC_address_Id());
//		address.setC_city(addressDTO.getC_city());
//		address.setC_state(addressDTO.getC_state());
//		address.setC_zipcode(addressDTO.getC_zipcode());
//		address.setCustomerDetails(custDetails);
//		
//		custDetails.setCustAddress(address);
//		bookings.setCustomerDetails(custDetails);
//		
//		VendorDetails vendorDetails = vendorDetailsRepository.findById(mybookingsDTO.getVendorDetails().getVendorId() )
//				.orElseThrow(() -> new RuntimeException("VendorId not found : " +mybookingsDTO.getVendorDetails().getVendorId()  ));
//		
//		VendorDetails vend = new VendorDetails();
//		vend.setVendorId(vendorDetails.getVendorId());
//		vend.setV_firstName(vendorDetails.getV_firstName());
//		vend.setV_lastName(vendorDetails.getV_lastName());
//		vend.setV_mobile(vendorDetails.getV_mobile());
//		vend.setV_email(vendorDetails.getV_email());
//		
//		
//		VendorAddressDTO vendorDTO = mybookingsDTO.getVendorDetails().getVendorAddress();
//		VendorAddress vaddress = new VendorAddress();
//		vaddress.setV_address1(vendorDTO.getV_address1());
////		vaddress.setV_address_id(vendorDTO.getV_address_Id());
//		vaddress.setV_city(vendorDTO.getV_city());
//		vaddress.setV_state(vendorDTO.getV_state());
//		vaddress.setV_zipcode(vendorDTO.getV_zipcode());
//		vaddress.setPricePerKg(vendorDTO.getPricePerKg());
//		vaddress.setBasePricePerKm(vendorDTO.getBasePricePerKm());
//		vaddress.setAvgDeliveryTimeInDays(vendorDTO.getAvgDeliveryTimeInDays());
//		vaddress.setEstimatedDeliveryDate(vendorDTO.getEstimatedDeliveryDate());
//		vaddress.setVendorDetails(vendorDetails);
//		vendorDetails.setVendorAddress(vaddress);
//		bookings.setVendorDetails(vendorDetails);
//		
//		bookings.setESTIMATED_WEIGHT(mybookingsDTO.getESTIMATED_WEIGHT());
//		bookings.setPICKUP_TIME_SLOT(mybookingsDTO.getPICKUP_TIME_SLOT());
//		bookings.setOTP_FOR_DELIVERY(mybookingsDTO.getOTP_FOR_DELIVERY());
//		bookings.setESTIMATED_COST(mybookingsDTO.getESTIMATED_COST());
//		bookings.setCREATED_BY(mybookingsDTO.getCREATED_BY());
//		bookings.setBOOKING_STATUS("CONFIRM");
//		bookings.setDELIVERY_DATE(mybookingsDTO.getDELIVERY_DATE());
//		bookings.setBOOKING_DATE(mybookingsDTO.getBOOKING_DATE());
//		bookings.setDISCOUNT_AMOUNT(mybookingsDTO.getDISCOUNT_AMOUNT());
//		bookings.setPAYMENT_MODE(mybookingsDTO.getPAYMENT_MODE());
//		bookings.setUPDATED_AT(mybookingsDTO.getUPDATED_AT());
//		bookings.setITEM_COUNT(mybookingsDTO.getITEM_COUNT());
//		bookings.setSCHEDULED_DATE(mybookingsDTO.getSCHEDULED_DATE());
//		bookings.setTRACKING_URL(mybookingsDTO.getTRACKING_URL());
//		bookings.setVEHICLE_NUMBER(mybookingsDTO.getVEHICLE_NUMBER());
//		bookings.setPAYMENT_STATUS(mybookingsDTO.getPAYMENT_STATUS());
//		bookings.setSERVICE_TYPE(mybookingsDTO.getSERVICE_TYPE());
//		bookings.setUPDATED_BY(mybookingsDTO.getUPDATED_BY());
//		bookings.setFINAL_COST(mybookingsDTO.getFINAL_COST());
//		bookings.setTRANSACTION_ID(mybookingsDTO.getTRANSACTION_ID());
//		
//		bookings.setPickup_latitude(mybookingsDTO.getPickup_latitude());
//		bookings.setPickup_longitude(mybookingsDTO.getPickup_longitude());
//		bookings.setPickup_address(mybookingsDTO.getPickup_address());
//		bookings.setPickup_zipcode(mybookingsDTO.getPickup_zipcode());
//		bookings.setDrop_latitude(mybookingsDTO.getDrop_latitude());
//		bookings.setDrop_longitude(mybookingsDTO.getDrop_longitude());
//		bookings.setDrop_address(mybookingsDTO.getDrop_address());
//		bookings.setDrop_zipcode(mybookingsDTO.getDrop_zipcode());
//		
//		MyBookings b = repository.save(bookings);
//		
//		MyBookingsResponseDTO response = mapper.toResponseDTO(b);
		
		logger.info("END : Booking Saved Succesfully : ");
		return null;
	}


	@Override
	@Transactional
	public HouseholdItemsResponse savebookingType(BookingTypeRequest request, HouseholdItemsResponse response,
			StatusHandler statusHandler) {
		logger.info("Start : Save booking type Service : "+request);
		List<HouseholdItemsDTO> itemsDTO = new ArrayList<>();
		
		
		try {
			Optional.ofNullable(request.getCustId()).orElseThrow( () ->  new InvalidRequestException("CUSTID_IS_REQUIRED") );
			Optional.ofNullable(request.getcMobile()).orElseThrow( () -> new InvalidRequestException("MOBILENUMBER_IS_REQUIRED"));
			Optional.ofNullable(request.getPickupLatitude()).orElseThrow( () -> new InvalidRequestException("LOCATION_IS_REQUIRED"));
			Optional.ofNullable(request.getPickupLongitude()).orElseThrow( () -> new InvalidRequestException("LOCATION_IS_REQUIRED"));
			Optional.ofNullable(request.getDropLatitude()).orElseThrow( () -> new InvalidRequestException("LOCATION_IS_REQUIRED"));
			Optional.ofNullable(request.getDropLongitude()).orElseThrow( () -> new InvalidRequestException("LOCATION_IS_REQUIRED"));
			MyBookings bookings = new MyBookings();
			bookings.setStatus("PENDING");
			bookings.setCustId(request.getCustId());
			bookings.setPickupAddress(request.getPickupAddress());
			bookings.setPickupLatitude(request.getPickupLatitude());
			bookings.setPickupLongitude(request.getPickupLongitude());
			bookings.setPickupZipcode(request.getPickupZipcode());
			bookings.setDropAddress(request.getDropAddress());
			bookings.setDropLatitude(request.getDropLatitude());
			bookings.setDropLongitude(request.getDropLongitude());
			bookings.setDropZipcode(request.getDropZipcode());
			bookings.setBookingDate(request.getBookingDate());
			bookings.setScheduledDate(request.getScheduledDate());
			bookings.setServiceType(request.getServiceType());
			bookings.setcMobile(request.getcMobile());
			bookings.setCreatedBy(request.getCustId());
			MyBookings booking = repository.save(bookings);
			
			if( bookings.getBookingId() < 0) {
				throw new RuntimeException(AppConstants.INSERT_QUERY_EXECUTION_FAILED);
			}
			
			response.setBookingId(booking.getBookingId());
			response.setStatus(AppConstants.PENDING);
			response.setCustId(booking.getCustId());
			response.setBookingDate(booking.getBookingDate());
			response.setPickupAddress(booking.getPickupAddress());
			response.setPickupLatitude(booking.getPickupLatitude());
			response.setPickupLongitude(booking.getPickupLongitude());
			response.setPickupZipcode(booking.getPickupZipcode());
			response.setDropAddress(booking.getDropAddress());
			response.setDropLatitude(booking.getDropLatitude());
			response.setDropLongitude(booking.getDropLongitude());
			response.setDropZipcode(booking.getDropZipcode());
			response.setScheduledDate(booking.getScheduledDate());
			response.setServiceType(booking.getServiceType());
			response.setcMobile(booking.getcMobile());
			
			logger.info("End Booking Items Saved Succesfully : "+booking);
			
			// Get HouseHold Items based on serviceType  
			logger.info("Start : Get Household Items : "+request.getServiceType());
			
			List<HouseholdItems> items =  houseRepository.findByEstCategoryIn(Arrays.asList("ONEBHK", "None"));
			
			itemsDTO = customerMapper.toitemsDtoList(items);
			statusHandler.setStatusCode("200");
			statusHandler.setMessage("SUCCESS");
			response.setStatusHandler(statusHandler);
			logger.info("END : Get Household Items : "+request.getServiceType());

		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			response.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			response.setStatusHandler(statusHandler);
		}
		
		response.setHouseholdItemsDTO(itemsDTO);
		logger.info("End : Save booking type Service : ");
		return response;
		
	}

	

	

}
