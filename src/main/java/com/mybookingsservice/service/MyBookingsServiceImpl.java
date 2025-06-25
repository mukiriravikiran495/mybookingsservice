package com.mybookingsservice.service;

import java.lang.invoke.MethodHandles;
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
import com.mybookingsservice.domain.AcceptBookingRequest;
import com.mybookingsservice.domain.AcceptBookingResponse;
import com.mybookingsservice.domain.BookingSummaryRequest;
import com.mybookingsservice.domain.BookingTransactionDTO;
import com.mybookingsservice.domain.BookingTransactionResponse;
import com.mybookingsservice.domain.BookingTypeRequest;
import com.mybookingsservice.domain.CustBookingResponse;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.CustomerResponse;
import com.mybookingsservice.domain.HouseholdItemsDTO;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsRequestDTO;
import com.mybookingsservice.domain.MyBookingsResponseDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorBookingsDTO;
import com.mybookingsservice.domain.VendorDTO;
import com.mybookingsservice.domain.VendorEstimateRequest;
import com.mybookingsservice.domain.VendorEstimateResponse;
import com.mybookingsservice.domain.VendorResponse;
import com.mybookingsservice.domain.VendorServiceAreaDTO;
import com.mybookingsservice.entity.BookingTransaction;
import com.mybookingsservice.entity.CustomerDetails;
import com.mybookingsservice.entity.HouseholdItems;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.SelectedItems;
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.mapper.BookingTransactionMapper;
import com.mybookingsservice.mapper.MyBookingsMapper;
import com.mybookingsservice.repository.BookingTransactionRepository;
import com.mybookingsservice.repository.CustomerRepository;
import com.mybookingsservice.repository.HouseholdRepository;
import com.mybookingsservice.repository.MyBookingsRepository;
import com.mybookingsservice.repository.VendorRepository;
import com.mybookingsservice.utils.CustomerUtils;
import com.mybookingsservice.utils.VendorUtils;

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
	VendorRepository vendorRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	BookingTransactionRepository transactionRepository;
	
//	@Autowired
//	VendorDetailsRepository vendorDetailsRepository;
	
	@Autowired
	BookingTransactionMapper transactionMapper;
	
	private final CustomerUtils customerUtils;
	private final VendorUtils vendorUtils;

    MyBookingsServiceImpl(SecurityConfig securityConfig, CustomerUtils customerUtils, VendorUtils vendorUtils) {
        this.securityConfig = securityConfig;
        this.customerUtils = customerUtils;
        this.vendorUtils = vendorUtils;
    }
	
	@Override
	public List<MyBookingsRequestDTO> getall() {
		
//		List<MyBookings> list = repository.findAllWithCustomerAndVendor();
//
//			return mapper.toDtoList(list);
		return null;
	}
	

	@Override
	public CustBookingResponse getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustBookingResponse custBookingResponse) {
		logger.info("START : Get Bookings By ID Service : "+custId+" "+bookingId);
		CustomerResponse custDetails = customerUtils.getCustomer(custId);
		
		System.out.println(custDetails.getDetailsDTO());
		if( null != custDetails.getDetailsDTO().getCustId()) {
			MyBookings bookings = repository.findByBookingIdAndCustId(custId, bookingId);
			
			logger.info("END : Get Bookings By ID Service : "+custId+" "+bookingId);
			MyBookingsRequestDTO booking = mapper.toDto(bookings);
			custBookingResponse.setRequestDTO(booking);
		}else {
			throw new RuntimeException("CustId you are passing is doesn't exists : "+custId);
		}
		
		return custBookingResponse;
	}

	@Override
	public CustBookingResponse cancelBookingById(Long custId, Long bookingId, CustBookingResponse cancelResponse, StatusHandler statusHandler) {
		logger.info("Start : Cancel Booking by Id Service : "+custId+" "+bookingId);
		
		
		try {
			CustomerResponse custDetails = customerUtils.getCustomer(custId);
			
			System.out.println(custDetails.getDetailsDTO());
			if( null == custDetails.getDetailsDTO().getCustId()) {
				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
			}
			MyBookings bookings = repository.findByBookingIdAndCustId(custId, bookingId);
			if( null == bookings) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			bookings.setStatus(AppConstants.CANCELLED);
			bookings.setBookingStatus(AppConstants.CANCELLED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(custId);
			MyBookings saved = repository.save(bookings);
			if( null != saved ) {
				MyBookingsRequestDTO dto = mapper.toDto(bookings);
				cancelResponse.setRequestDTO(dto);
				statusHandler.setStatusCode("200");
				statusHandler.setMessage(AppConstants.SUCCESS);
				cancelResponse.setStatusHandler(statusHandler);
			}	
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(AppConstants.INVALID_REQUEST);
			cancelResponse.setStatusHandler(statusHandler);
			return cancelResponse;
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelResponse.setStatusHandler(statusHandler);
			return cancelResponse;
		}
		logger.info("End : cancel booking By Id Service : "+cancelResponse);
		return cancelResponse;
	}

	public CustomerBookingResponseDTO getBookingsByCustomerId(Long custId, CustomerBookingResponseDTO response, StatusHandler statusHandler) {
		logger.info("Start : get all bookings service fro custId : "+custId);
		try {
			CustomerResponse custDetails = customerUtils.getCustomer(custId);
			
			System.out.println(custDetails.getDetailsDTO());
			if( null == custDetails.getDetailsDTO().getCustId()) {
				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
			}
			List<MyBookings> bookings = repository.findByCustIdWithItems(custId);
	        System.out.println(" Selected : "+bookings.get(0).getSelectedItems());
	        List<MyBookingsRequestDTO> dtoList = mapper.toCustomerBookingDTOs(bookings);
	        response.setRequestDTO(dtoList);
		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			response.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			response.setStatusHandler(statusHandler);
		}
        
        
        logger.info("End : get all bookings service from custId : "+custId);
		return response;
    }

	@Override
	public CustomerBookingResponseDTO getAllCancelledBookings(Long custId, String status,
			CustomerBookingResponseDTO cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : get all cancelled bookings service for custId : "+custId);
		try {
			CustomerResponse custDetails = customerUtils.getCustomer(custId);
			
			System.out.println(custDetails.getDetailsDTO());
			if( null == custDetails.getDetailsDTO().getCustId()) {
				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
			}
			List<MyBookings> bookings = repository.findByCustIdAndStatusWithItems(custId, status);
			List<MyBookingsRequestDTO> dtoList = mapper.toCustomerBookingDTOs(bookings);
			cancelledBookings.setRequestDTO(dtoList);
		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelledBookings.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelledBookings.setStatusHandler(statusHandler);
		}
		
		
		logger.info("END : get all cancelled bookings service for custId : "+custId);
		return cancelledBookings;
	}

	@Override
	public VendorBookingsDTO getBookingByVendorId(Long vendorId, VendorBookingsDTO vendor, StatusHandler statusHandler) {
		logger.info("START : VendorBookings Service ");
		try {
			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
			
			System.out.println(vendorDetails.getVendorDTO());
			if( null == vendorDetails.getVendorDTO().getVendorId()) {
				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
			}
			List<MyBookings> bookings = repository.findByVendorIdWithItems(vendorId);
			List<MyBookingsRequestDTO> dtoList = mapper.toCustomerBookingDTOs(bookings);
			vendor.setRequestDTO(dtoList);
		}catch(RuntimeException ex) {
			
		}catch(Exception ex) {
			
		}
		
		logger.info("END : VendorBookings Service : ");
//	    return vendorMapper.toVendorBookingDTOs(bookings);
		return vendor;
	}


	@Override
	public VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler) {
		logger.info("Start : get bookings by vendorId and bookingId service : "+vendorId+" "+bookingId);
		try {
			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
			
			System.out.println(vendorDetails.getVendorDTO());
			if( null == vendorDetails.getVendorDTO().getVendorId()) {
				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
			}
			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
			MyBookingsRequestDTO booking = mapper.toDto(bookings);
			vendorbooking.setRequestDTO(booking);
		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(AppConstants.VENDORID_DOES_NOT_EXISTS);
			vendorbooking.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			vendorbooking.setStatusHandler(statusHandler);
		}
		
		logger.info("End : get bookings by vendorId and bookingId service : "+vendorId+" "+bookingId);
		return vendorbooking;
	}

	
	@Override
	public VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, 
			VendorBookingResponseDTO vendorResponse, StatusHandler statusHandler) {
		logger.info("Start : Cancel Bookings by Vendor Service : "+vendorId+" "+bookingId);
		
		try {
			if( null == vendorId || null == bookingId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
			
			System.out.println(vendorDetails.getVendorDTO());
			if( null == vendorDetails.getVendorDTO().getVendorId()) {
				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
			}
			
			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
			
			bookings.setStatus(AppConstants.CANCELLED);
			bookings.setBookingStatus(AppConstants.CANCELLED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			MyBookings saved = repository.save(bookings);
			if( null != saved ) {
				MyBookingsRequestDTO dto = mapper.toDto(bookings);
				vendorResponse.setRequestDTO(dto);
				statusHandler.setStatusCode("200");
				statusHandler.setMessage(AppConstants.SUCCESS);
				vendorResponse.setStatusHandler(statusHandler);
			}
		}catch(RuntimeException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.VENDORID_DOES_NOT_EXISTS);
			vendorResponse.setStatusHandler(statusHandler);
			return vendorResponse;
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.INVALID_REQUEST);
			vendorResponse.setStatusHandler(statusHandler);
			return vendorResponse;
		}catch(Exception ex ) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			vendorResponse.setStatusHandler(statusHandler);
			return vendorResponse;
		}
		
		logger.info("End : Cancel Bookings by Vendor Service : "+vendorId+" "+bookingId);
		
		return vendorResponse;
	}


	@Override
	public VendorBookingsDTO getVendorCancelledBookings(Long vendorId, String status,
			VendorBookingsDTO cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : Get all Vendor Cancelled Bookings : "+vendorId);
		VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
		
		System.out.println(vendorDetails.getVendorDTO());
		if( null == vendorDetails.getVendorDTO().getVendorId()) {
			throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
		}
		List<MyBookings> bookings = repository.findByVendorIdAndStatusWithItems(vendorId, status);
		List<MyBookingsRequestDTO> dtoList = mapper.toCustomerBookingDTOs(bookings);
		cancelledBookings.setRequestDTO(dtoList);
		logger.info("End : Get all Vendor Cancelled Bookings : "+vendorId);
		
		return cancelledBookings;
	}


	@Override
	public AcceptBookingResponse acceptBooking(AcceptBookingRequest acceptBookingRequest, AcceptBookingResponse acceptBookingResponse, StatusHandler statusHandler) {
		logger.info("Start : Accept Bookings Service : "+acceptBookingRequest);
//		
//		MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//		bookings.setStatus(AppConstants.ACCEPTED);
//		bookings.setUPDATED_AT(new Timestamp(System.currentTimeMillis()));
		MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(acceptBookingRequest.getBookingId(), 
				acceptBookingRequest.getCustId(), acceptBookingRequest.getVendorId());
		bookings.setStatus(AppConstants.ACCEPTED);
		bookings.setBookingStatus(AppConstants.ACCEPTED);
		bookings.setUpdatedAt(LocalDateTime.now());
		bookings.setUpdatedBy(acceptBookingRequest.getVendorId());
		MyBookings updatedBooking = repository.save(bookings);
		
		MyBookingsRequestDTO dto =  mapper.toDto(updatedBooking);
		acceptBookingResponse.setMybookingsDTO(dto);
		logger.info("End : Accept Bookings Service : "+acceptBookingResponse);
//		return vendorMapper.toVendorBookingDTO(bookings);
		return acceptBookingResponse;
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
		VendorEstimateResponse response = new VendorEstimateResponse();
		try {
			if( null == request.getPickupLatitude() || null == request.getPickupLongitude() || null == request.getDropLatitude() || null == request.getDropLongitude()) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			List<VendorNativeResult> flatResult = vendorRepository.findVendorsByZipcodeNative(request.getPickupZipcode());
			Optional.ofNullable(flatResult).orElseThrow(() ->  new RuntimeException(AppConstants.VENDRO_SERVICES_NOT_AVAILABLE));
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

			
			response.setBookingDate(LocalDateTime.now());
			response.setScheduledDate(request.getScheduledDate());
			response.setServicetype(request.getServiceType());

			response.setPickupAddress(request.getPickupAddress());
			response.setPickupLatitude(request.getPickupLatitude());
			response.setPickupLongitude(request.getPickupLongitude());
			response.setPickupZipcode(request.getPickupZipcode());
			response.setDropZipcode(request.getDropZipcode());
			response.setDropAddress(request.getDropAddress());
			response.setDropLatitude(request.getDropLatitude());
			response.setDropLongitude(request.getDropLongitude());

			response.setVendorDTO(new ArrayList<>(vendorMap.values()));

			StatusHandler status = new StatusHandler();
			status.setStatusCode("200");
			status.setMessage(AppConstants.SUCCESS);
			response.setStatusHandler(status);

			
		}catch(Exception ex) {
			
		}
		return response;

		

	}

	
	@Override
	@Transactional
	public MyBookingsResponseDTO createBookings(MyBookingsRequestDTO mybookingsRequestDTO, MyBookingsResponseDTO myBookingsResponse,
			StatusHandler statusHandler) {
		logger.info("Start : Create Bookings Service : "+mybookingsRequestDTO);
		
		try {
			MyBookings bookings = repository.findAllByBookingId(mybookingsRequestDTO.getBookingId());
			Optional.ofNullable(bookings).orElseThrow( () -> new RuntimeException(AppConstants.BOOKINGID_NOT_FOUND));
			bookings.setStatus(AppConstants.CONFRIRMED);

			bookings.setcFirstname(mybookingsRequestDTO.getcFirstname());
			bookings.setcLastname(mybookingsRequestDTO.getcLastname());
			bookings.setcEmail(mybookingsRequestDTO.getcEmail());
			bookings.setcAddressId(mybookingsRequestDTO.getcAddressId());
			bookings.setcAddress1(mybookingsRequestDTO.getcAddress1());
			bookings.setcCity(mybookingsRequestDTO.getcCity());
			bookings.setcState(mybookingsRequestDTO.getcState());
			bookings.setcZipcode(mybookingsRequestDTO.getcZipcode());
			
			bookings.setVendorId(mybookingsRequestDTO.getVendorId());
			bookings.setvFirstname(mybookingsRequestDTO.getvFirstname());
			bookings.setvLastname(mybookingsRequestDTO.getvLastname());
			bookings.setvMobile(mybookingsRequestDTO.getvMobile());
			bookings.setvEmail(mybookingsRequestDTO.getvEmail());
			bookings.setvAddressId(mybookingsRequestDTO.getvAddressId());
			bookings.setvAddress1(mybookingsRequestDTO.getvAddress1());
			bookings.setvCity(mybookingsRequestDTO.getvCity());
			bookings.setvState(mybookingsRequestDTO.getvState());
			bookings.setvZipcode(mybookingsRequestDTO.getvZipcode());
			bookings.setBasePricePerKM(mybookingsRequestDTO.getBasePricePerKM());
			bookings.setPricePerKG(mybookingsRequestDTO.getPricePerKG());
			bookings.setAvgDeliveryTimeInDays(mybookingsRequestDTO.getAvgDeliveryTimeInDays());
			bookings.setEstimatedPrice(mybookingsRequestDTO.getEstimatedPrice());
			bookings.setEstimatedDeliverydate(mybookingsRequestDTO.getEstimatedDeliverydate());

			bookings.setPickupAddress(mybookingsRequestDTO.getPickupAddress());
			bookings.setPickupLatitude(mybookingsRequestDTO.getPickupLatitude());
			bookings.setPickupLongitude(mybookingsRequestDTO.getPickupLongitude());
			bookings.setPickupZipcode(mybookingsRequestDTO.getPickupZipcode());
			bookings.setDropAddress(mybookingsRequestDTO.getDropAddress());
			bookings.setDropLatitude(mybookingsRequestDTO.getDropLatitude());
			bookings.setDropLongitude(mybookingsRequestDTO.getDropLongitude());
			bookings.setDropZipcode(mybookingsRequestDTO.getDropZipcode());
			bookings.setEstimatedWeight(mybookingsRequestDTO.getEstimatedWeight());
			bookings.setPickupTimeSlot(mybookingsRequestDTO.getPickupTimeSlot());
			bookings.setOtpforDelivery(mybookingsRequestDTO.getOtpforDelivery());
			bookings.setEstimatedCost(mybookingsRequestDTO.getEstimatedCost());
			bookings.setCreatedAt(LocalDateTime.now());
			bookings.setBookingStatus(mybookingsRequestDTO.getBookingStatus());

			bookings.setCreatedBy(mybookingsRequestDTO.getCreatedBy());
			bookings.setDeliveryDate(mybookingsRequestDTO.getDeliveryDate());
			bookings.setBookingDate(mybookingsRequestDTO.getBookingDate());
			bookings.setDiscountAmount(mybookingsRequestDTO.getDiscountAmount());
			bookings.setPaymentMode(mybookingsRequestDTO.getPaymentMode());
			bookings.setUpdatedAt(mybookingsRequestDTO.getUpdatedAt());
			bookings.setUpdatedBy(mybookingsRequestDTO.getUpdatedBy());
			bookings.setItemCount(mybookingsRequestDTO.getItemCount());
			bookings.setScheduledDate(mybookingsRequestDTO.getScheduledDate());
			bookings.setTrackingUrl(mybookingsRequestDTO.getTrackingUrl());
			bookings.setVehicleNumber(mybookingsRequestDTO.getVehicleNumber());
			bookings.setPaymentStatus(mybookingsRequestDTO.getPaymentStatus());
			bookings.setServiceType(mybookingsRequestDTO.getServiceType());
			bookings.setFinalCost(mybookingsRequestDTO.getFinalCost());
			bookings.setTransactionId(mybookingsRequestDTO.getTransactionId());

			List<SelectedItems> itemsList = mapper.toEntityList(mybookingsRequestDTO.getSelectedItems());
			List<SelectedItems> selectedList = new ArrayList<>();
			for(SelectedItems item : itemsList) {
				SelectedItems selected = new SelectedItems();
				selected.setItemId(item.getItemId());
				selected.setItemCode(item.getItemCode());
				selected.setItemName(item.getItemName());
				selected.setCategory(item.getCategory());
				selected.setEstCategory(item.getEstCategory());
				selected.setWeight(item.getWeight());
				selected.setQty(item.getQty());
				selected.setCreatedBy(bookings.getCustId());
				selected.setUpdatedBy(item.getUpdatedBy());
				selected.setCreatedAt(mybookingsRequestDTO.getCreatedAt());
				selected.setUpdatedAt(mybookingsRequestDTO.getUpdatedAt());
				selected.setBooking(bookings);
				selectedList.add(selected);	
			}
			bookings.setSelectedItems(selectedList);
			MyBookings savedBookings = repository.save(bookings);
			
			if( null != savedBookings) {
				
				CustomerDetails details = customerRepository.findByCustId(mybookingsRequestDTO.getCustId());
				details.setcFirstname(mybookingsRequestDTO.getcFirstname());
				details.setcCity(mybookingsRequestDTO.getcCity());
				details.setcState(mybookingsRequestDTO.getcState());
				details.setcZipcode(mybookingsRequestDTO.getPickupZipcode());
				customerRepository.save(details);
				MyBookingsRequestDTO dto = mapper.toDto(savedBookings);
				myBookingsResponse.setMyBookingRequestDTO(dto);
				statusHandler.setStatusCode("200");
				statusHandler.setMessage(AppConstants.SUCCESS);
				myBookingsResponse.setStatusHandler(statusHandler);
			}
			
			
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			myBookingsResponse.setStatusHandler(statusHandler);
		}
			
		logger.info("END : Booking Saved Succesfully : ");
		return myBookingsResponse;
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
			
			CustomerResponse custDetails = customerUtils.getCustomer(request.getCustId());
			
			System.out.println(custDetails.getDetailsDTO());
			if( null == custDetails.getDetailsDTO().getCustId()) {
				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
			}
			
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
			bookings.setPricePerKG(0);
			bookings.setBasePricePerKM(0);
			bookings.setEstimatedPrice(0);
			bookings.setAvgDeliveryTimeInDays(0);
			
			MyBookings booking = repository.save(bookings);

			CustomerDetails details = customerRepository.findByCustId(request.getCustId());
			details.setcAddress1(request.getPickupAddress());
			details.setPickupLattitude(request.getPickupLatitude());
			details.setPickupLongitude(request.getPickupLongitude());
			details.setcZipcode(request.getPickupZipcode());
			customerRepository.save(details);
			
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
			response.setPricePerKG(booking.getPricePerKG());
			response.setBasePricePerKM(booking.getBasePricePerKM());
			response.setEstimatedPrice(booking.getEstimatedPrice());
			response.setAvgDeliveryTimeInDays(booking.getAvgDeliveryTimeInDays());
			
			logger.info("End Booking Items Saved Succesfully : "+booking);
			
			// Get HouseHold Items based on serviceType  
			logger.info("Start : Get Household Items : "+request.getServiceType());
			
			List<HouseholdItems> items =  houseRepository.findByEstCategoryIn(Arrays.asList("ONEBHK", "None"));
			
			itemsDTO = mapper.toitemsDtoList(items);
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

	@Override
	@Transactional
	public VendorBookingResponseDTO updatePickup(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBooking,
			StatusHandler statusHandler) {
		logger.info("Start : update pickup service : "+vendorId);
		
		try {
			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
			if(null == bookingId) {
				throw new RuntimeException("VendorId and bookingId is Null : ");
			}
			bookings.setStatus(AppConstants.PICKUP_COMPLETED);
			bookings.setBookingStatus(AppConstants.PICKUP_COMPLETED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			MyBookings saved = repository.save(bookings);
			MyBookingsRequestDTO booking = mapper.toDto(saved);
			vendorBooking.setRequestDTO(booking);
			if( null != saved) {
				statusHandler.setErrorCode("200");
				statusHandler.setErrorMessage(AppConstants.SUCCESS);
				vendorBooking.setStatusHandler(statusHandler);
			}
			
		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			vendorBooking.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			vendorBooking.setStatusHandler(statusHandler);
		}
		
		
		logger.info("End : update pickup service : "+vendorId);
		return vendorBooking;
	}

	@Override
	public VendorBookingResponseDTO updateDrop(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBooking,
			StatusHandler statusHandler) {
		logger.info("Start : update drop service : "+vendorId+" "+bookingId);
		try {
			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
			if(null == bookingId) {
				throw new RuntimeException("VendorId and bookingId is Null : ");
			}
			bookings.setStatus(AppConstants.DROP_COMPLETED);
			bookings.setBookingStatus(AppConstants.DROP_COMPLETED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			MyBookings saved = repository.save(bookings);
			MyBookingsRequestDTO booking = mapper.toDto(saved);
			vendorBooking.setRequestDTO(booking);
			if( null != saved) {
				statusHandler.setErrorCode("200");
				statusHandler.setErrorMessage(AppConstants.SUCCESS);
				vendorBooking.setStatusHandler(statusHandler);
			}
			
		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			vendorBooking.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			vendorBooking.setStatusHandler(statusHandler);
		}
		
		logger.info("End : update drop service : "+vendorId+" "+bookingId);
		return vendorBooking;
	}

	@Override
	public BookingTransactionResponse createTransaction(BookingTransactionDTO dto, BookingTransactionResponse response,
			StatusHandler statusHandler) {
		logger.info("Start : create transaction service : "+dto);
		
		MyBookings booking = repository.findById(dto.getBookingId())
	            .orElseThrow(() -> new RuntimeException("Booking ID not found: " + dto.getBookingId()));
		BookingTransaction transaction = new BookingTransaction();
		transaction.setBooking(booking); // ✅ Set the actual entity, not just ID
	    transaction.setTransactionRef(dto.getTransactionRef());
	    transaction.setTransactionType(dto.getTransactionType());
	    transaction.setAmount(dto.getAmount());
	    transaction.setCurrency(dto.getCurrency());
	    transaction.setStatus(dto.getStatus());
	    transaction.setPaymentMode(dto.getPaymentMode());
	    transaction.setResponseMessage(dto.getResponseMessage());
	    transaction.setCreatedAt(LocalDateTime.now());
	    transaction.setCreatedBy(dto.getCreatedBy());
	    BookingTransaction  tran = transactionRepository.save(transaction);
	    BookingTransactionDTO newDto = transactionMapper.toDTO(tran);
	    newDto.setBookingId(dto.getBookingId());
		response.setTransactionDTO(newDto);
		
//		BookingTransaction transaction = transactionMapper.toEntity(dto);
//		System.out.println(transaction.toString());
//		BookingTransaction  tran = transactionRepository.save(transaction);
//		BookingTransactionDTO newDto = transactionMapper.toDTO(tran);
//		response.setTransactionDTO(newDto);
		logger.info("End : create transaction service : "+dto);
		return response;
	}

	@Override
	public MyBookingsResponseDTO getBookingSummary(BookingSummaryRequest bookingSummary, MyBookingsResponseDTO response,
			StatusHandler statusHandler) {
		logger.info("Start : bokking summary service : "+bookingSummary);
		MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(bookingSummary.getBookingId(), bookingSummary.getCustId(), bookingSummary.getVendorId());
		MyBookingsRequestDTO dto = mapper.toDto(bookings);
		response.setMyBookingRequestDTO(dto);
		logger.info("End : booking summary service : "+bookingSummary);
		return response;
	}

	

	

}
