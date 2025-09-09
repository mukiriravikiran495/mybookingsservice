package com.mybookingsservice.service;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mybookingsservice.config.SecurityConfig;
import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.AcceptBookingRequest;
import com.mybookingsservice.domain.AcceptBookingResponse;
import com.mybookingsservice.domain.AvailableItemsDTO;
import com.mybookingsservice.domain.AvailableVehiclesDTO;
import com.mybookingsservice.domain.BookingSummaryRequest;
import com.mybookingsservice.domain.BookingTransactionDTO;
import com.mybookingsservice.domain.BookingTransactionResponse;
import com.mybookingsservice.domain.ConfirmBookingRequest;
import com.mybookingsservice.domain.ConfirmBookingResponse;
import com.mybookingsservice.domain.CustBookingResponse;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.CustomerDetailsDTO;
import com.mybookingsservice.domain.CustomerResponse;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.MyBookingsRequest;
import com.mybookingsservice.domain.MyBookingsResponse;
import com.mybookingsservice.domain.TranLogResponse;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorBookingsDTO;
import com.mybookingsservice.domain.VendorEstimateRequest;
import com.mybookingsservice.domain.VendorEstimateResponse;
import com.mybookingsservice.entity.Applications;
import com.mybookingsservice.entity.AvailableItems;
import com.mybookingsservice.entity.AvailableVehicles;
import com.mybookingsservice.entity.CustomerTokens;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.mapper.BookingTransactionMapper;
import com.mybookingsservice.mapper.MyBookingsMapper;
import com.mybookingsservice.repository.ApplicationsRepository;
import com.mybookingsservice.repository.AvailableItemsRepository;
import com.mybookingsservice.repository.AvailableVehicleRepository;
import com.mybookingsservice.repository.BookingTransactionRepository;
import com.mybookingsservice.repository.CustomerRepository;
import com.mybookingsservice.repository.HouseholdRepository;
import com.mybookingsservice.repository.MyBookingsRepository;
import com.mybookingsservice.repository.VendorRepository;
import com.mybookingsservice.utils.CustomerUtils;
import com.mybookingsservice.utils.TransactionUtils;
import com.mybookingsservice.utils.VendorUtils;

@Service("MyBookingsService")
public class MyBookingsServiceImpl implements MyBookingsService {

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

	@Autowired
	ApplicationsRepository applicationsRepository;

//	@Autowired
//	VendorDetailsRepository vendorDetailsRepository;

	@Autowired
	BookingTransactionMapper transactionMapper;

	@Autowired
	TransactionUtils transactionUtils;

	@Autowired
	AvailableVehicleRepository availableVehicleRepository;
	
	@Autowired
	AvailableItemsRepository availableItemsRepository;

	private final CustomerUtils customerUtils;
	private final VendorUtils vendorUtils;

	MyBookingsServiceImpl(SecurityConfig securityConfig, CustomerUtils customerUtils, VendorUtils vendorUtils) {
		this.securityConfig = securityConfig;
		this.customerUtils = customerUtils;
		this.vendorUtils = vendorUtils;
	}

	@Override
	public List<MyBookingsRequest> getall() {

//		List<MyBookings> list = repository.findAllWithCustomerAndVendor();
//
//			return mapper.toDtoList(list);
		return null;
	}

	public static Predicate<MyBookings> isCancelled() {
		return booking -> "CANCELLED".equalsIgnoreCase(booking.getBookingStatus());
	}

	public static Predicate<MyBookings> isConfirmed() {
		return booking -> "CONFIRMED".equalsIgnoreCase(booking.getBookingStatus());
	}

	public static Predicate<MyBookings> isPending() {
		return booking -> "PENDING".equalsIgnoreCase(booking.getBookingStatus());
	}

	@Override
	public CustBookingResponse getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustBookingResponse custBookingResponse) {
		logger.info("START : Get Bookings By ID Service : " + custId + " " + bookingId);
		CustomerResponse custDetails = customerUtils.findCustomer(custId, "");

//		System.out.println(custDetails.getDetailsDTO());
//		if (null != custDetails.getDetailsDTO().getCustId()) {
//			MyBookings bookings = repository.findByBookingIdAndCustId(custId, bookingId);
//
//			logger.info("END : Get Bookings By ID Service : " + custId + " " + bookingId);
//			MyBookingsRequest booking = mapper.toDto(bookings);
//			custBookingResponse.setRequestDTO(booking);
//		} else {
//			throw new RuntimeException("CustId you are passing is doesn't exists : " + custId);
//		}

		return custBookingResponse;
	}

	@Override
	public CustBookingResponse cancelBookingById(Long custId, Long bookingId, CustBookingResponse cancelResponse,
			StatusHandler statusHandler) {
		logger.info("Start : Cancel Booking by Id Service : " + custId + " " + bookingId);

//		try {
//			CustomerResponse custDetails = customerUtils.getCustomer(custId);
//
//			System.out.println(custDetails.getDetailsDTO());
//			if (null == custDetails.getDetailsDTO().getCustId()) {
//				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//			}
//			MyBookings bookings = repository.findByBookingIdAndCustId(custId, bookingId);
//			if (null == bookings) {
//				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
//			}
//			bookings.setStatus(AppConstants.CANCELLED);
//			bookings.setBookingStatus(AppConstants.CANCELLED);
//			bookings.setUpdatedAt(LocalDateTime.now());
//			bookings.setUpdatedBy(custId);
//			MyBookings saved = repository.save(bookings);
//			if (null != saved) {
//				MyBookingsRequest dto = mapper.toDto(bookings);
//				cancelResponse.setRequestDTO(dto);
//				statusHandler.setStatusCode("200");
//				statusHandler.setMessage(AppConstants.SUCCESS);
//				cancelResponse.setStatusHandler(statusHandler);
//			}
//		} catch (InvalidRequestException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(AppConstants.INVALID_REQUEST);
//			cancelResponse.setStatusHandler(statusHandler);
//			return cancelResponse;
//		} catch (Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(ex.getMessage());
//			cancelResponse.setStatusHandler(statusHandler);
//			return cancelResponse;
//		}
		logger.info("End : cancel booking By Id Service : " + cancelResponse);
		return cancelResponse;
	}

	public CustomerBookingResponseDTO getBookingsByCustomerId(Long custId, CustomerBookingResponseDTO response,
			StatusHandler statusHandler) {
		logger.info("Start : get all bookings service fro custId : " + custId);
//		try {
//			CustomerResponse custDetails = customerUtils.getCustomer(custId);
//
//			System.out.println(custDetails.getDetailsDTO());
//			if (null == custDetails.getDetailsDTO().getCustId()) {
//				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//			}
//
//			List<MyBookings> bookings = repository.findByCustIdWithItems(custId).stream().collect(Collectors.toList());
//			System.out.println(" Selected : " + bookings.get(0).getSelectedItems());
//			List<MyBookingsRequest> dtoList = mapper.toCustomerBookingDTOs(bookings);
//			response.setRequestDTO(dtoList);
//		} catch (RuntimeException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(ex.getMessage());
//			response.setStatusHandler(statusHandler);
//		} catch (Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(ex.getMessage());
//			response.setStatusHandler(statusHandler);
//		}

		logger.info("End : get all bookings service from custId : " + custId);
		return response;
	}

	@Override
	public CustomerBookingResponseDTO getAllCancelledBookings(Long custId, String status,
			CustomerBookingResponseDTO cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : get all cancelled bookings service for custId : " + custId);
//		try {
//			CustomerResponse custDetails = customerUtils.getCustomer(custId);
//
//			System.out.println(custDetails.getDetailsDTO());
//			if (null == custDetails.getDetailsDTO().getCustId()) {
//				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//			}
//			List<MyBookings> bookings = repository.findByCustIdAndStatusWithItems(custId, status);
//			List<MyBookingsRequest> dtoList = bookings.stream().filter(isCancelled()).map(mapper::toDto)
//					.collect(Collectors.toList());
//			cancelledBookings.setRequestDTO(dtoList);
//		} catch (RuntimeException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(ex.getMessage());
//			cancelledBookings.setStatusHandler(statusHandler);
//		} catch (Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(ex.getMessage());
//			cancelledBookings.setStatusHandler(statusHandler);
//		}

		logger.info("END : get all cancelled bookings service for custId : " + custId);
		return cancelledBookings;
	}

	@Override
	public VendorBookingsDTO getBookingByVendorId(Long vendorId, VendorBookingsDTO vendor,
			StatusHandler statusHandler) {
		logger.info("START : VendorBookings Service ");
//		try {
//			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
//
//			System.out.println(vendorDetails.getVendorDTO());
//			if (null == vendorDetails.getVendorDTO().getVendorId()) {
//				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			}
//			List<MyBookings> bookings = repository.findByVendorIdWithItems(vendorId);
//			List<MyBookingsRequest> dtoList = mapper.toCustomerBookingDTOs(bookings);
//			vendor.setRequestDTO(dtoList);
//		} catch (RuntimeException ex) {
//
//		} catch (Exception ex) {
//
//		}

		logger.info("END : VendorBookings Service : ");
//	    return vendorMapper.toVendorBookingDTOs(bookings);
		return vendor;
	}

	@Override
	public VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler) {
		logger.info("Start : get bookings by vendorId and bookingId service : " + vendorId + " " + bookingId);
//		try {
//			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
//
//			System.out.println(vendorDetails.getVendorDTO());
//			if (null == vendorDetails.getVendorDTO().getVendorId()) {
//				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			}
//			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//			MyBookingsRequest booking = mapper.toDto(bookings);
//			vendorbooking.setRequestDTO(booking);
//		} catch (RuntimeException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			vendorbooking.setStatusHandler(statusHandler);
//		} catch (Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(ex.getMessage());
//			vendorbooking.setStatusHandler(statusHandler);
//		}

		logger.info("End : get bookings by vendorId and bookingId service : " + vendorId + " " + bookingId);
		return vendorbooking;
	}

	@Override
	public VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorResponse, StatusHandler statusHandler) {
		logger.info("Start : Cancel Bookings by Vendor Service : " + vendorId + " " + bookingId);

//		try {
//			if (null == vendorId || null == bookingId) {
//				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
//			}
//
//			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
//
//			System.out.println(vendorDetails.getVendorDTO());
//			if (null == vendorDetails.getVendorDTO().getVendorId()) {
//				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			}
//
//			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//
//			bookings.setStatus(AppConstants.CANCELLED);
//			bookings.setBookingStatus(AppConstants.CANCELLED);
//			bookings.setUpdatedAt(LocalDateTime.now());
//			bookings.setUpdatedBy(vendorId);
//			MyBookings saved = repository.save(bookings);
//			if (null != saved) {
//				MyBookingsRequest dto = mapper.toDto(bookings);
//				vendorResponse.setRequestDTO(dto);
//				statusHandler.setStatusCode("200");
//				statusHandler.setMessage(AppConstants.SUCCESS);
//				vendorResponse.setStatusHandler(statusHandler);
//			}
//		} catch (RuntimeException ex) {
//			statusHandler.setStatusCode("400");
//			statusHandler.setMessage(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			vendorResponse.setStatusHandler(statusHandler);
//			return vendorResponse;
//		} catch (InvalidRequestException ex) {
//			statusHandler.setStatusCode("400");
//			statusHandler.setMessage(AppConstants.INVALID_REQUEST);
//			vendorResponse.setStatusHandler(statusHandler);
//			return vendorResponse;
//		} catch (Exception ex) {
//			statusHandler.setStatusCode("500");
//			statusHandler.setMessage(ex.getMessage());
//			vendorResponse.setStatusHandler(statusHandler);
//			return vendorResponse;
//		}

		logger.info("End : Cancel Bookings by Vendor Service : " + vendorId + " " + bookingId);

		return vendorResponse;
	}

	@Override
	public VendorBookingsDTO getVendorCancelledBookings(Long vendorId, String status,
			VendorBookingsDTO cancelledBookings, StatusHandler statusHandler) {
		logger.info("Start : Get all Vendor Cancelled Bookings : " + vendorId);

//		VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
//
//		System.out.println(vendorDetails.getVendorDTO());
//		if (null == vendorDetails.getVendorDTO().getVendorId()) {
//			throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//		}
//		List<MyBookings> bookings = repository.findByVendorIdAndStatusWithItems(vendorId, status);
//		List<MyBookingsRequest> dtoList = bookings.stream().filter(isCancelled()).map(mapper::toDto)
//				.collect(Collectors.toList());
//		cancelledBookings.setRequestDTO(dtoList);
		logger.info("End : Get all Vendor Cancelled Bookings : " + vendorId);

		return cancelledBookings;
	}

	@Override
	public AcceptBookingResponse acceptBooking(AcceptBookingRequest acceptBookingRequest,
			AcceptBookingResponse acceptBookingResponse, StatusHandler statusHandler) {
		logger.info("Start : Accept Bookings Service : " + acceptBookingRequest);

//		CustomerResponse custDetails = customerUtils.getCustomer(acceptBookingRequest.getCustId());
//		System.out.println(custDetails.getDetailsDTO());
//		if (null == custDetails.getDetailsDTO().getCustId()) {
//			throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//		}
//		VendorResponse vendorDetails = vendorUtils.getVendor(acceptBookingRequest.getVendorId());
//		System.out.println(vendorDetails.getVendorDTO());
//		if (null == vendorDetails.getVendorDTO().getVendorId()) {
//			throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//		}
//
//		MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(acceptBookingRequest.getBookingId(),
//				acceptBookingRequest.getCustId(), acceptBookingRequest.getVendorId());
//		bookings.setStatus(AppConstants.ACCEPTED);
//		bookings.setBookingStatus(AppConstants.ACCEPTED);
//		bookings.setUpdatedAt(LocalDateTime.now());
//		bookings.setUpdatedBy(acceptBookingRequest.getVendorId());
//		MyBookings updatedBooking = repository.save(bookings);
//
//		MyBookingsRequest dto = mapper.toDto(updatedBooking);
//		acceptBookingResponse.setMybookingsDTO(dto);
//		logger.info("End : Accept Bookings Service : " + acceptBookingResponse);
////		return vendorMapper.toVendorBookingDTO(bookings);
		return acceptBookingResponse;
	}

	@Override
	public HouseholdItemsResponse getHouseHoldItems(String estCategory, HouseholdItemsResponse itemResponse,
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
//		try {
//			if (null == request.getPickupLatitude() || null == request.getPickupLongitude()
//					|| null == request.getDropLatitude() || null == request.getDropLongitude()) {
//				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
//			}
//
//			CustomerResponse custDetails = customerUtils.getCustomer(request.getCustId());
//			System.out.println(custDetails.getDetailsDTO());
//			Optional.ofNullable(custDetails.getDetailsDTO().getCustId()).orElseThrow(() -> new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS));
//			
//
//			response = vendorUtils.getvendorEstimates(request);
//
//			StatusHandler status = new StatusHandler();
//			status.setStatusCode("200");
//			status.setMessage(AppConstants.SUCCESS);
//			response.setStatusHandler(status);
//
//		} catch (Exception ex) {
//
//		}
		return response;

	}

	@Override
	@Transactional
	public MyBookingsResponse saveVehicleBookings(MyBookingsRequest mybookingsRequest,
			MyBookingsResponse myBookingsResponse, String token, String appId, StatusHandler statusHandler) {
		logger.info("Start : Create Bookings Service : " + mybookingsRequest);

		// Validate AccessToken
		CustomerTokens custTokens = customerUtils.validateAccessToken(token);
		System.out.println(custTokens);
		// validate app
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		try {
			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getCustId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.CUSTID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.CUSTID_IS_REQUIRED);
			});
			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcMobile()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.MOBILENUMBER_IS_REQUIRED);
				return new InvalidRequestException("MOBILENUMBER_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcPickupLongitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcDropLatitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcDropLongitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			// Validate CustId in Customer Details
			CustomerResponse custResponse = customerUtils.findCustomer(mybookingsRequest.getMyBookingsDTO().getCustId(),
					token);
			System.out.println(custResponse.getCustomerDetailsDTO());

			Optional.ofNullable(custResponse.getCustomerDetailsDTO().getCustId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, AppConstants.CUSTID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.CUSTID_IS_REQUIRED);
			});

			// Save Booking details
			CustomerDetailsDTO custDetailsDTO = custResponse.getCustomerDetailsDTO();
			MyBookingsDTO bookingsDTO = new MyBookingsDTO();
			System.out.println("customer details: " + custDetailsDTO);
			System.out.println("success");

			bookingsDTO.setBookingStatus(AppConstants.PENDING);
			bookingsDTO.setBookingDate(LocalDateTime.now());
			bookingsDTO.setShiftDate(LocalDateTime.now());
			bookingsDTO.setVendorType(mybookingsRequest.getMyBookingsDTO().getVendorType());
			bookingsDTO.setBookingType(mybookingsRequest.getMyBookingsDTO().getBookingType());
			bookingsDTO.setCustId(custDetailsDTO.getCustId());
			bookingsDTO.setcFirstName(custDetailsDTO != null ? custDetailsDTO.getcFirstname() : null);
			bookingsDTO.setcLastName(custDetailsDTO != null ? custDetailsDTO.getcLastname() : null);
			bookingsDTO.setcMobile(custDetailsDTO != null ? custDetailsDTO.getcMobile() : null);
			bookingsDTO.setcEmail(custDetailsDTO != null ? custDetailsDTO.getcEmail() : null);
			bookingsDTO.setcAddress1(custDetailsDTO != null ? custDetailsDTO.getcAddress1() : null);
			bookingsDTO.setcAddress2(custDetailsDTO != null ? custDetailsDTO.getcAddress2() : null);
			bookingsDTO.setcCity(custDetailsDTO != null ? custDetailsDTO.getcCity() : null);
			bookingsDTO.setcState(custDetailsDTO != null ? custDetailsDTO.getcState() : null);
			bookingsDTO.setcZipCode(custDetailsDTO != null ? custDetailsDTO.getcZipcode() : null);
			bookingsDTO.setcPickupLatitude(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude());
			bookingsDTO.setcPickupLongitude(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude());
			bookingsDTO.setCreatedAt(LocalDateTime.now());
			bookingsDTO.setCreatedBy(custDetailsDTO != null ? custDetailsDTO.getCustId() : null);

			MyBookings bookingsEntity = mapper.toEntity(bookingsDTO);

			MyBookings bookingsSaved = null;
			try {
				bookingsSaved = repository.save(bookingsEntity);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// Calculate fare details
			double distanceKm = calculateDistance(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude(),
					mybookingsRequest.getMyBookingsDTO().getcPickupLongitude(),
					mybookingsRequest.getMyBookingsDTO().getcDropLatitude(),
					mybookingsRequest.getMyBookingsDTO().getcDropLongitude());
			System.out.println("Distance in KM: "+distanceKm);
			List<AvailableVehiclesDTO> vehicleDTOList = new ArrayList<>();
			List<AvailableVehicles> vehiclesList = availableVehicleRepository.findAll();
			for (int i = 0; i < vehiclesList.size(); i++) {
				AvailableVehiclesDTO vehicle = new AvailableVehiclesDTO();
				vehicle.setVehicleId(vehiclesList.get(i).getVehicleId());
				vehicle.setVehicleName(vehiclesList.get(i).getVehicleName());
				vehicle.setVehicleWeight(vehiclesList.get(i).getVehicleWeight());
				vehicle.setVehicleWeightUnit(vehiclesList.get(i).getVehicleWeightUnit());
				vehicle.setLength(vehiclesList.get(i).getLength());
				vehicle.setWidth(vehiclesList.get(i).getWidth());
				vehicle.setHeight(vehiclesList.get(i).getHeight());
				vehicle.setVehicleSizeUnit(vehiclesList.get(i).getVehicleSizeUnit());
				vehicle.setBaseFare(vehiclesList.get(i).getBaseFare());
				vehicle.setPerKmRate(vehiclesList.get(i).getPerKmRate());
				vehicle.setCreatedAt(vehiclesList.get(i).getCreatedAt());
				vehicle.setCreatedBy(vehiclesList.get(i).getCreatedBy());
				BigDecimal fare = vehiclesList.get(i).getBaseFare()
						.add(vehiclesList.get(i).getPerKmRate().multiply(BigDecimal.valueOf(distanceKm)));
				System.out.println("Fare: " + fare + " " + "distance: " + distanceKm);
				vehicle.setFare(fare.setScale(2, RoundingMode.HALF_UP));
				vehicle.setDistance(distanceKm);
				vehicleDTOList.add(vehicle);

			}

			MyBookingsDTO newBookingsDTO = mapper.toDTO(bookingsSaved);
			System.out.println("BookingId: " + newBookingsDTO.getBookingId());
			myBookingsResponse.setCustId(bookingsSaved.getCustId());
			myBookingsResponse.setBookingId(bookingsSaved.getBookingId());
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			myBookingsResponse.setStatusHandler(statusHandler);
			myBookingsResponse.setAvaiableVehiclesDTO(vehicleDTOList);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.VEHICLE_BOOKING_FORM, AppConstants.SUCCESS, null);
		} catch (Exception e) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorCode(e.getMessage());
			myBookingsResponse.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, e.getMessage());
			return myBookingsResponse;
		}
		return myBookingsResponse;
	}

	@Override
	@Transactional
	public HouseholdItemsResponse savePandMBookings(MyBookingsRequest mybookingsRequest, HouseholdItemsResponse response, String accessToken, String appId,
			StatusHandler statusHandler) {
		logger.info("Start : Save booking type Service : " + mybookingsRequest);
		// Validate AccessToken
		CustomerTokens custTokens = customerUtils.validateAccessToken(accessToken);
		System.out.println(custTokens);
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		try {
			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getCustId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.CUSTID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.CUSTID_IS_REQUIRED);
			});
			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcMobile()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.MOBILENUMBER_IS_REQUIRED);
				return new InvalidRequestException("MOBILENUMBER_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcPickupLongitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcDropLatitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			Optional.ofNullable(mybookingsRequest.getMyBookingsDTO().getcDropLongitude()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.LOCATION_IS_REQUIRED);
				return new InvalidRequestException("LOCATION_IS_REQUIRED");
			});

			// Validate CustId in Customer Details
			CustomerResponse custResponse = customerUtils.findCustomer(mybookingsRequest.getMyBookingsDTO().getCustId(),
					accessToken);
			System.out.println(custResponse.getCustomerDetailsDTO());

			Optional.ofNullable(custResponse.getCustomerDetailsDTO().getCustId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, AppConstants.CUSTID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.CUSTID_IS_REQUIRED);
			});

			// Save Booking details
			CustomerDetailsDTO custDetailsDTO = custResponse.getCustomerDetailsDTO();
			MyBookingsDTO bookingsDTO = new MyBookingsDTO();
			System.out.println("customer details: " + custDetailsDTO);
			System.out.println("success");

			bookingsDTO.setBookingStatus(AppConstants.PENDING);
			bookingsDTO.setBookingDate(LocalDateTime.now());
			bookingsDTO.setShiftDate(mybookingsRequest.getMyBookingsDTO().getShiftDate());
			bookingsDTO.setVendorType(mybookingsRequest.getMyBookingsDTO().getVendorType());
			bookingsDTO.setBookingType(mybookingsRequest.getMyBookingsDTO().getBookingType());
			bookingsDTO.setCustId(custDetailsDTO.getCustId());
			bookingsDTO.setcFirstName(custDetailsDTO != null ? custDetailsDTO.getcFirstname() : null);
			bookingsDTO.setcLastName(custDetailsDTO != null ? custDetailsDTO.getcLastname() : null);
			bookingsDTO.setcMobile(custDetailsDTO != null ? custDetailsDTO.getcMobile() : null);
			bookingsDTO.setcEmail(custDetailsDTO != null ? custDetailsDTO.getcEmail() : null);
			bookingsDTO.setcAddress1(custDetailsDTO != null ? custDetailsDTO.getcAddress1() : null);
			bookingsDTO.setcAddress2(custDetailsDTO != null ? custDetailsDTO.getcAddress2() : null);
			bookingsDTO.setcCity(custDetailsDTO != null ? custDetailsDTO.getcCity() : null);
			bookingsDTO.setcState(custDetailsDTO != null ? custDetailsDTO.getcState() : null);
			bookingsDTO.setcZipCode(custDetailsDTO != null ? custDetailsDTO.getcZipcode() : null);
			bookingsDTO.setcPickupLatitude(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude());
			bookingsDTO.setcPickupLongitude(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude());
			bookingsDTO.setcDropLatitude(mybookingsRequest.getMyBookingsDTO().getcDropLatitude());
			bookingsDTO.setcDropLongitude(mybookingsRequest.getMyBookingsDTO().getcDropLongitude());
			bookingsDTO.setCreatedAt(LocalDateTime.now());
			bookingsDTO.setCreatedBy(custDetailsDTO != null ? custDetailsDTO.getCustId() : null);

			MyBookings bookingsEntity = mapper.toEntity(bookingsDTO);

			MyBookings bookingsSaved = null;
			try {
				bookingsSaved = repository.save(bookingsEntity);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// Calculate fare details
			double distanceKm = calculateDistance(mybookingsRequest.getMyBookingsDTO().getcPickupLatitude(),
					mybookingsRequest.getMyBookingsDTO().getcPickupLongitude(),
					mybookingsRequest.getMyBookingsDTO().getcDropLatitude(),
					mybookingsRequest.getMyBookingsDTO().getcDropLongitude());
			System.out.println("Distance in KM: "+distanceKm);
			
			//Fetch Item details
			List<AvailableItems> itemsList = availableItemsRepository.findAll();
			List<AvailableItemsDTO> itemsDTOList = new ArrayList<>();
			
			for (int i = 0; i < itemsList.size(); i++) {
				AvailableItemsDTO item = new AvailableItemsDTO();
				item.setItemId(itemsList.get(i).getItemId());
				item.setItemName(itemsList.get(i).getItemName());
				item.setItemCategory(itemsList.get(i).getItemCategory());
				item.setItemWeight(itemsList.get(i).getItemWeight());
				item.setItemWeightUnit(itemsList.get(i).getItemWeightUnit());
				item.setCreatedAt(itemsList.get(i).getCreatedAt());
				item.setCreatedBy(itemsList.get(i).getCreatedBy());
				item.setUpdatedAt(itemsList.get(i).getUpdatedAt());
				item.setUpdatedBy(itemsList.get(i).getUpdatedBy());
				itemsDTOList.add(item);

			}
			
			
			MyBookingsDTO newBookingsDTO = mapper.toDTO(bookingsSaved);
			System.out.println("BookingId: " + newBookingsDTO.getBookingId());
			response.setCustId(mybookingsRequest.getMyBookingsDTO().getCustId());
			response.setBookingId(bookingsSaved.getBookingId());
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			response.setStatusHandler(statusHandler);
			response.setAvailableItemsDTO(itemsDTOList);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.PANDM_BOOKING_FORM, AppConstants.SUCCESS, null);
		} catch (Exception e) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorCode(e.getMessage());
			response.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, e.getMessage());
			return response;
		}
		
		
		logger.info("End : Save booking type Service : " + response);
		return response;

	}
	
	@Override
	public ConfirmBookingResponse confirmTruckBooking(ConfirmBookingRequest confirmBookingRequest,
			ConfirmBookingResponse confirmBookingresponse, String token, String appId, StatusHandler statusHandler) {
		logger.info("Start : Save booking type Service : " + confirmBookingRequest);
		CustomerTokens custTokens = customerUtils.validateAccessToken(token);
		System.out.println(custTokens);
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		try {
			Optional.ofNullable(confirmBookingRequest.getMyBookingsDTO().getBookingId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, AppConstants.BOOKINGID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.BOOKINGID_IS_REQUIRED);
			});
			Optional.ofNullable(confirmBookingRequest.getMyBookingsDTO().getCustId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, AppConstants.CUSTID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.CUSTID_IS_REQUIRED);
			});
			Optional.ofNullable(confirmBookingRequest.getAvailableVehiclesDTO().getFare()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, AppConstants.FARE_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.FARE_IS_REQUIRED);
			});
			Optional.ofNullable(confirmBookingRequest.getAvailableVehiclesDTO().getVehicleId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, AppConstants.VEHICLEID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.VEHICLEID_IS_REQUIRED);
			});
			Optional.ofNullable(confirmBookingRequest.getAvailableVehiclesDTO().getVehicleName()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, AppConstants.VEHICLE_NAME_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.VEHICLE_NAME_IS_REQUIRED);
			});
			
			confirmBookingresponse = vendorUtils.findNearestTrucks(confirmBookingRequest.getMyBookingsDTO().getcPickupLatitude(), 
					confirmBookingRequest.getMyBookingsDTO().getcPickupLongitude(), token, appId);
			
			
			
		}catch (Exception e) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorCode(e.getMessage());
			confirmBookingresponse.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, e.getMessage());
			return confirmBookingresponse;
		}
		
		
		logger.info("End : Save booking type Service : " + confirmBookingresponse);
		return confirmBookingresponse;
	}
	
	private double calculateDistance(Double cPickupLatitude, Double cPickupLongitude, Double cDropLatitude,
			Double cDropLongitude)  {
		String API_KEY = "AIzaSyAQHOAqNwhjEdcMoOMwHRFRkoYh9WUcehk";
		String BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";

	    // Log coordinates for debugging
	    System.out.printf("Origin: %f,%f | Destination: %f,%f%n",
	            cPickupLatitude, cPickupLongitude, cDropLatitude, cDropLongitude);

	    // Build request URL
	    String url = String.format("%s?origins=%f,%f&destinations=%f,%f&key=%s",
	            BASE_URL, cPickupLatitude, cPickupLongitude, cDropLatitude, cDropLongitude, API_KEY);

	    System.out.println("Calling Google API: " + url);

	    RestTemplate restTemplate = new RestTemplate();
	    String response = restTemplate.getForObject(url, String.class);

	    try {
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(response);

	        // Get first element in rows -> elements
	        JSONArray rows = (JSONArray) json.get("rows");
	        JSONObject row0 = (JSONObject) rows.get(0);
	        JSONArray elements = (JSONArray) row0.get("elements");
	        JSONObject element0 = (JSONObject) elements.get(0);

	        String status = (String) element0.get("status");
	        if (!"OK".equals(status)) {
	            throw new RuntimeException("Error fetching distance from Google API: " + status);
	        }

	        // Extract distance in meters
	        JSONObject distanceObj = (JSONObject) element0.get("distance");
	        Number distanceValue = (Number) distanceObj.get("value"); // safer casting
	        double distanceInKm = distanceValue.doubleValue() / 1000.0;

	        System.out.printf("Distance: %.2f km%n", distanceInKm);
	        return distanceInKm;

	    } catch (Exception e) {
	        throw new RuntimeException("Error parsing distance response: " + e.getMessage(), e);
	    }
	}
	
	@Override
	@Transactional
	public VendorBookingResponseDTO updatePickup(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBooking,
			StatusHandler statusHandler) {
		logger.info("Start : update pickup service : " + vendorId);

//		try {
//
//			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
//			System.out.println(vendorDetails.getVendorDTO());
//			if (null == vendorDetails.getVendorDTO().getVendorId()) {
//				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			}
//			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//			if (null == bookingId) {
//				throw new RuntimeException("VendorId and bookingId is Null : ");
//			}
//			CustomerResponse custDetails = customerUtils.getCustomer(bookings.getCustId());
//			System.out.println(custDetails.getDetailsDTO());
//			if (null == custDetails.getDetailsDTO().getCustId()) {
//				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//			}
//			bookings.setStatus(AppConstants.PICKUP_COMPLETED);
//			bookings.setBookingStatus(AppConstants.PICKUP_COMPLETED);
//			bookings.setUpdatedAt(LocalDateTime.now());
//			bookings.setUpdatedBy(vendorId);
//			MyBookings saved = repository.save(bookings);
//			MyBookingsRequest booking = mapper.toDto(saved);
//			vendorBooking.setRequestDTO(booking);
//			if (null != saved) {
//				statusHandler.setErrorCode("200");
//				statusHandler.setErrorMessage(AppConstants.SUCCESS);
//				vendorBooking.setStatusHandler(statusHandler);
//			}
//
//		} catch (RuntimeException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(ex.getMessage());
//			vendorBooking.setStatusHandler(statusHandler);
//		} catch (Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(ex.getMessage());
//			vendorBooking.setStatusHandler(statusHandler);
//		}
//
//		logger.info("End : update pickup service : " + vendorId);
		return vendorBooking;
	}

	@Override
	public VendorBookingResponseDTO updateDrop(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBooking,
			StatusHandler statusHandler) {
		logger.info("Start : update drop service : " + vendorId + " " + bookingId);
//		try {
//
//			VendorResponse vendorDetails = vendorUtils.getVendor(vendorId);
//
//			System.out.println(vendorDetails.getVendorDTO());
//			if (null == vendorDetails.getVendorDTO().getVendorId()) {
//				throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//			}
//			MyBookings bookings = repository.findByBookingIdAndVendorId(vendorId, bookingId);
//			if (null == bookingId) {
//				throw new RuntimeException("VendorId and bookingId is Null : ");
//			}
//			CustomerResponse custDetails = customerUtils.getCustomer(bookings.getCustId());
//
//			System.out.println(custDetails.getDetailsDTO());
//			if (null == custDetails.getDetailsDTO().getCustId()) {
//				throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//			}
//			bookings.setStatus(AppConstants.DROP_COMPLETED);
//			bookings.setBookingStatus(AppConstants.DROP_COMPLETED);
//			bookings.setUpdatedAt(LocalDateTime.now());
//			bookings.setUpdatedBy(vendorId);
//			MyBookings saved = repository.save(bookings);
//			MyBookingsRequest booking = mapper.toDto(saved);
//			vendorBooking.setRequestDTO(booking);
//			if (null != saved) {
//				statusHandler.setErrorCode("200");
//				statusHandler.setErrorMessage(AppConstants.SUCCESS);
//				vendorBooking.setStatusHandler(statusHandler);
//			}
//
//		} catch (RuntimeException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(ex.getMessage());
//			vendorBooking.setStatusHandler(statusHandler);
//		} catch (Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(ex.getMessage());
//			vendorBooking.setStatusHandler(statusHandler);
//		}

		logger.info("End : update drop service : " + vendorId + " " + bookingId);
		return vendorBooking;
	}

	@Override
	public BookingTransactionResponse createTransaction(BookingTransactionDTO dto, BookingTransactionResponse response,
			StatusHandler statusHandler) {
		logger.info("Start : create transaction service : " + dto);

//		MyBookings booking = repository.findById(dto.getBookingId())
//				.orElseThrow(() -> new RuntimeException("Booking ID not found: " + dto.getBookingId()));
//
//		CustomerResponse custDetails = customerUtils.getCustomer(booking.getCustId());
//		System.out.println(custDetails.getDetailsDTO());
//		if (null == custDetails.getDetailsDTO().getCustId()) {
//			throw new RuntimeException(AppConstants.CUSTID_DOES_NOT_EXISTS);
//		}
//
//		VendorResponse vendorDetails = vendorUtils.getVendor(booking.getVendorId());
//		System.out.println(vendorDetails.getVendorDTO());
//		if (null == vendorDetails.getVendorDTO().getVendorId()) {
//			throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//		}
//
//		BookingTransaction transaction = new BookingTransaction();
//		transaction.setBooking(booking); // ✅ Set the actual entity, not just ID
//		transaction.setTransactionRef(dto.getTransactionRef());
//		transaction.setTransactionType(dto.getTransactionType());
//		transaction.setAmount(dto.getAmount());
//		transaction.setCurrency(dto.getCurrency());
//		transaction.setStatus(dto.getStatus());
//		transaction.setPaymentMode(dto.getPaymentMode());
//		transaction.setResponseMessage(dto.getResponseMessage());
//		transaction.setCreatedAt(LocalDateTime.now());
//		transaction.setCreatedBy(dto.getCreatedBy());
//		BookingTransaction tran = transactionRepository.save(transaction);
//		BookingTransactionDTO newDto = transactionMapper.toDTO(tran);
//		newDto.setBookingId(dto.getBookingId());
//		response.setTransactionDTO(newDto);
//
////		BookingTransaction transaction = transactionMapper.toEntity(dto);
////		System.out.println(transaction.toString());
////		BookingTransaction  tran = transactionRepository.save(transaction);
////		BookingTransactionDTO newDto = transactionMapper.toDTO(tran);
////		response.setTransactionDTO(newDto);
		logger.info("End : create transaction service : " + dto);
		return response;
	}

	@Override
	public MyBookingsResponse getBookingSummary(BookingSummaryRequest bookingSummary, MyBookingsResponse response,
			StatusHandler statusHandler) {
		logger.info("Start : bokking summary service : " + bookingSummary);
//		MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(bookingSummary.getBookingId(),
//				bookingSummary.getCustId(), bookingSummary.getVendorId());
//		MyBookingsRequest dto = mapper.toDto(bookings);
//		response.setMyBookingRequestDTO(dto);
		logger.info("End : booking summary service : " + bookingSummary);
		return response;
	}


}
