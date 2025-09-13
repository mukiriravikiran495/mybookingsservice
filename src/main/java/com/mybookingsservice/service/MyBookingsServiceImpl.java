package com.mybookingsservice.service;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
import com.mybookingsservice.domain.AvailableItemsDTO;
import com.mybookingsservice.domain.AvailablePandMDTO;
import com.mybookingsservice.domain.AvailableVehiclesDTO;
import com.mybookingsservice.domain.BookingSummaryRequest;
import com.mybookingsservice.domain.BookingTransactionDTO;
import com.mybookingsservice.domain.BookingTransactionResponse;
import com.mybookingsservice.domain.ConfirmBookingRequest;
import com.mybookingsservice.domain.ConfirmBookingResponse;
import com.mybookingsservice.domain.CustomerDetailsDTO;
import com.mybookingsservice.domain.CustomerDetailsDTOResponse;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.MyBookingsRequest;
import com.mybookingsservice.domain.MyBookingsResponse;
import com.mybookingsservice.domain.SelectPackersAndMoversRequest;
import com.mybookingsservice.domain.SelectPackersAndMoversResponse;
import com.mybookingsservice.domain.SelectedItemsDTO;
import com.mybookingsservice.domain.TranLogResponse;
import com.mybookingsservice.domain.VehicleDetailsDTO;
import com.mybookingsservice.domain.VehicleSearchRequest;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorDetailsDTO;
import com.mybookingsservice.domain.VendorsListResponse;
import com.mybookingsservice.entity.Applications;
import com.mybookingsservice.entity.AvailableItems;
import com.mybookingsservice.entity.AvailableVehicles;
import com.mybookingsservice.entity.CustomerTokens;
import com.mybookingsservice.entity.MyBookings;
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.entity.VendorTokens;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.exceptions.TokenPersistenceException;
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
	public HouseholdItemsResponse getHouseHoldItems(String estCategory, HouseholdItemsResponse itemResponse,
			StatusHandler statusHandler) {

		return null;
	}

	@Override
	public Vendor getvendorProfile(long vendorId) {

		return null;
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
			CustomerDetailsDTOResponse custResponse = customerUtils.findCustomer(mybookingsRequest.getMyBookingsDTO().getCustId(),
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
			bookingsDTO.setVehicleType(mybookingsRequest.getMyBookingsDTO().getVehicleType());
			bookingsDTO.setPickupOtpIsVerified("N");
			bookingsDTO.setDropOtpIsVerified("N");
			String pickupOTP = generateOTP();
			String dropOTP = generateOTP();
			bookingsDTO.setPickupOTP(pickupOTP);
			bookingsDTO.setDropOTP(dropOTP);
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
			System.out.println("Distance in KM: " + distanceKm);
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
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			myBookingsResponse.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.VEHICLE_BOOKING_FORM, AppConstants.FAILED, e.getMessage());
			return myBookingsResponse;
		}
		return myBookingsResponse;
	}

	@Override
	@Transactional
	public HouseholdItemsResponse savePandMBookings(MyBookingsRequest mybookingsRequest,
			HouseholdItemsResponse response, String accessToken, String appId, StatusHandler statusHandler) {
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
			CustomerDetailsDTOResponse custResponse = customerUtils.findCustomer(mybookingsRequest.getMyBookingsDTO().getCustId(),
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
			bookingsDTO.setPickupOtpIsVerified("N");
			bookingsDTO.setDropOtpIsVerified("N");
			String pickupOTP = generateOTP();
			String dropOTP = generateOTP();
			bookingsDTO.setPickupOTP(pickupOTP);
			bookingsDTO.setDropOTP(dropOTP);
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
			System.out.println("Distance in KM: " + distanceKm);

			// Fetch Item details
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
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			response.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.PANDM_BOOKING_FORM, AppConstants.FAILED, e.getMessage());
			return response;
		}

		logger.info("End : Save booking type Service : " + response);
		return response;

	}
	
	private String generateOTP() {
		Random random = new Random();
		int otp = 1000 + random.nextInt(9000); // ensures 4-digit OTP
		return String.valueOf(otp);
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
						AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED,
						AppConstants.VEHICLE_NAME_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.VEHICLE_NAME_IS_REQUIRED);
			});
			// Validate CustId in Customer Details
			CustomerDetailsDTOResponse custResponse = customerUtils
					.findCustomer(confirmBookingRequest.getMyBookingsDTO().getCustId(), token);
			System.out.println(custResponse.getCustomerDetailsDTO());
			
			VehicleSearchRequest vehicleSearchRequest = new VehicleSearchRequest();
			vehicleSearchRequest.setcCity(confirmBookingRequest.getMyBookingsDTO().getcCity());
			vehicleSearchRequest.setcPickupLatitude(confirmBookingRequest.getMyBookingsDTO().getcPickupLatitude());
			vehicleSearchRequest.setcPickupLongitude(confirmBookingRequest.getMyBookingsDTO().getcPickupLongitude());
			vehicleSearchRequest.setVehicleType(confirmBookingRequest.getMyBookingsDTO().getVehicleType());
			vehicleSearchRequest.setcZipCode(confirmBookingRequest.getMyBookingsDTO().getcZipCode());
			vehicleSearchRequest.setcState(confirmBookingRequest.getMyBookingsDTO().getcState());
			confirmBookingresponse = vendorUtils.findNearestTrucks(vehicleSearchRequest, token, appId);

			VehicleDetailsDTO vehicleDetailsDTO = new VehicleDetailsDTO();

		} catch (Exception e) {
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			confirmBookingresponse.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.CONFIRM_VEHICLE_BOOKING, AppConstants.FAILED, e.getMessage());
			return confirmBookingresponse;
		}

		logger.info("End : Save booking type Service : " + confirmBookingresponse);
		return confirmBookingresponse;
	}

	private double calculateDistance(Double cPickupLatitude, Double cPickupLongitude, Double cDropLatitude,
			Double cDropLongitude) {
		String API_KEY = "AIzaSyAQHOAqNwhjEdcMoOMwHRFRkoYh9WUcehk";
		String BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";

		// Log coordinates for debugging
		System.out.printf("Origin: %f,%f | Destination: %f,%f%n", cPickupLatitude, cPickupLongitude, cDropLatitude,
				cDropLongitude);

		// Build request URL
		String url = String.format("%s?origins=%f,%f&destinations=%f,%f&key=%s", BASE_URL, cPickupLatitude,
				cPickupLongitude, cDropLatitude, cDropLongitude, API_KEY);

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
	
	public SelectPackersAndMoversResponse selectPandMBooking(
			SelectPackersAndMoversRequest selectPackersAndMoversRequest,
			SelectPackersAndMoversResponse selectPackersAndMoversResponse, String token, String appId,
			StatusHandler statusHandler) {
		logger.info("Start: create pandm bookings service: "+selectPackersAndMoversRequest);
		//Validate Access token
		CustomerTokens custTokens = customerUtils.validateAccessToken(token);
		System.out.println(custTokens);
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		
		try {
			Optional.ofNullable(selectPackersAndMoversRequest.getMyBookingsDTO().getBookingId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_PACKERS_AND_MOVERS_BOOKING, AppConstants.FAILED, AppConstants.BOOKINGID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.BOOKINGID_IS_REQUIRED);
			});
			Optional.ofNullable(selectPackersAndMoversRequest.getMyBookingsDTO().getCustId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
						AppConstants.CONFIRM_PACKERS_AND_MOVERS_BOOKING, AppConstants.FAILED, AppConstants.CUSTID_IS_REQUIRED);
				return new InvalidRequestException(AppConstants.CUSTID_IS_REQUIRED);
			});
			
			// Validate CustId in Customer Details
			CustomerDetailsDTOResponse custResponse = customerUtils
					.findCustomer(selectPackersAndMoversRequest.getMyBookingsDTO().getCustId(), token);
			System.out.println(custResponse.getCustomerDetailsDTO());
			
			MyBookings bookings = repository.findByBookingId(selectPackersAndMoversRequest.getMyBookingsDTO().getBookingId());
			
			
			//Fetch Available Packers and Movers in city and isOnline
			long isOnline = 1;
			VendorsListResponse vendorsListResponse = vendorUtils
					.findAvailablePackersAndMovers(selectPackersAndMoversRequest.getMyBookingsDTO().getcCity(), token, isOnline);
			System.out.println("List: "+vendorsListResponse);
			
			// Calculate distance
			double distanceKm = calculateDistance(selectPackersAndMoversRequest.getMyBookingsDTO().getcPickupLatitude(),
								selectPackersAndMoversRequest.getMyBookingsDTO().getcPickupLongitude(),
								selectPackersAndMoversRequest.getMyBookingsDTO().getcDropLatitude(),
								selectPackersAndMoversRequest.getMyBookingsDTO().getcDropLongitude());
			
			
			
			//Map to AvailablePandMDTO
			List<VendorDetailsDTO> vendorsList = vendorsListResponse.getVendorDetailsDTO();
			List<AvailablePandMDTO> availableList = new ArrayList<>();
			double fare = 0 ;
			for(int i = 0 ; i < vendorsList.size() ; i++) {
				
				AvailablePandMDTO pandmDTO = new AvailablePandMDTO();
				pandmDTO.setVendorId(vendorsList.get(i).getVendorId());
				pandmDTO.setCompanyName(vendorsList.get(i).getCompanyName());
				pandmDTO.setOwnerName(vendorsList.get(i).getOwnerName());
				pandmDTO.setVpimageurl(vendorsList.get(i).getvPImageUrl());
				pandmDTO.setVmobile(vendorsList.get(i).getvMobile());
				//calculate Fare details
				double baseFare = vendorsListResponse.getVendorDetailsDTO().get(i).getBaseFare(); // base booking charge
				double perKgRate = vendorsListResponse.getVendorDetailsDTO().get(i).getPerKgRate(); // per km
				double perKmRate = vendorsListResponse.getVendorDetailsDTO().get(i).getPerKmRate();  // per kg
				fare = calculateFare(distanceKm, selectPackersAndMoversRequest.getSelectedItemsDTO(), baseFare, perKgRate, perKmRate);
				System.out.println("Fare: "+fare);
				pandmDTO.setFare(fare);
				pandmDTO.setDiscountType("percentage");
				pandmDTO.setCompanyDiscount(10l);
				pandmDTO.setVendorDiscount(10l);
				availableList.add(pandmDTO);
			}
			selectPackersAndMoversResponse.setAvailablePandMDTO(availableList);
			bookings.setFare(fare);
			MyBookings savedBookings = repository.save(bookings);
			System.out.println(savedBookings.getBookingId());
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.CONFIRM_PACKERS_AND_MOVERS_BOOKING, AppConstants.SUCCESS, null);
		} catch (Exception e) {
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			selectPackersAndMoversResponse.setStatusHandler(statusHandler);
			TranLogResponse tranlogResponse = transactionUtils.savetruckBookingTranLog(custTokens,
					AppConstants.CONFIRM_PACKERS_AND_MOVERS_BOOKING, AppConstants.FAILED, e.getMessage());
			return selectPackersAndMoversResponse;
		}
		
		logger.info("End : Save booking type Service : " + selectPackersAndMoversResponse);
		return selectPackersAndMoversResponse;
	}

	private double calculateFare(double distanceKm, List<SelectedItemsDTO> selectedItemsDTO, double baseFare,
			double perKgRate, double perKmRate) {
		double fare = baseFare;
		// 1. Distance charge
		// 1. Distance charge
        fare += perKgRate * distanceKm;

        // 2. Weight charge
        double totalWeight = 0.0;
        for (SelectedItemsDTO item : selectedItemsDTO) {
            double itemWeight = item.getItemWeight().doubleValue() * item.getItemQuantity();
            totalWeight += itemWeight;
        }
        fare += perKgRate * totalWeight;

        // 3. Service charge (e.g. 10%)
//        double serviceCharge = fare * (serviceChargePercent / 100.0);
//        fare += serviceCharge;

        return fare;
	}

	@Override
	@Transactional
	public VendorBookingResponseDTO updatePickup(Long vendorId, Long bookingId, Long custId, String otp,
			VendorBookingResponseDTO vendorBooking, StatusHandler statusHandler, String token, String appId) {
		logger.info("Start : update pickup service : " + vendorId + " : " + bookingId + " : " + custId);
		VendorTokens vendorTokens = vendorUtils.validateAccessToken(token);
		VendorBookingResponseDTO vendorBookingResponseDTO = new VendorBookingResponseDTO();
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		
		
		try {
			//Validate Access token
			System.out.println(vendorTokens);
			Optional.ofNullable(vendorTokens.getVendorId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
						AppConstants.UPDATE_TRUCK_PICKUP, AppConstants.FAILED, AppConstants.INVALID_TOKEN);
				return new TokenPersistenceException(AppConstants.INVALID_TOKEN);
			});
			
			//Validate custId and vendorId
			MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(bookingId, custId, vendorId)
				    .orElseThrow(() -> {
				    	TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
								AppConstants.UPDATE_TRUCK_PICKUP, AppConstants.FAILED, AppConstants.CUSTID_AND_VENDORID_DOES_NOT_EXISTS);
				    	return	new InvalidRequestException("Booking not found for given customer/vendor");
			});
			
			if(!bookings.getPickupOTP().equalsIgnoreCase(otp)) {
				throw new InvalidRequestException(AppConstants.INVALID_OTP);
			}
			
			//update Pickup
			bookings.setBookingStatus(AppConstants.PICKUP_COMPLETED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			bookings.setPickupOtpIsVerified("Y");
			MyBookings bookingsSaved = null;
			try {
				bookingsSaved = repository.save(bookings);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_PICKUP, AppConstants.SUCCESS, null);
			statusHandler.setStatusCode("400");
			statusHandler.setError(AppConstants.SUCCESS);
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			MyBookingsDTO newBookingsDTO = mapper.toDTO(bookingsSaved);
			vendorBookingResponseDTO.setMyBookingsDTO(newBookingsDTO);
		}catch(TokenPersistenceException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_PICKUP, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(InvalidRequestException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_PICKUP, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(Exception e) {
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_PICKUP, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}	
		logger.info("End : update pickup service : " + vendorBooking);

		return vendorBookingResponseDTO;
	}
	
	@Override
	public VendorBookingResponseDTO customerLocated(Long vendorId, Long bookingId, Long custId,
			VendorBookingResponseDTO vendorBooking, StatusHandler statusHandler, String token, String appId) {
		logger.info("Start : update pickup service : " + vendorId + " : " + bookingId + " : " + custId);
		VendorTokens vendorTokens = vendorUtils.validateAccessToken(token);
		VendorBookingResponseDTO vendorBookingResponseDTO = new VendorBookingResponseDTO();
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		
		
		try {
			//Validate Access token
			System.out.println(vendorTokens);
			Optional.ofNullable(vendorTokens.getVendorId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
						AppConstants.UPDATE_CUSTOMER_LOCATED, AppConstants.FAILED, AppConstants.INVALID_TOKEN);
				return new TokenPersistenceException(AppConstants.INVALID_TOKEN);
			});
			
			//Validate custId and vendorId
			MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(bookingId, custId, vendorId)
				    .orElseThrow(() -> {
				    	TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
								AppConstants.UPDATE_CUSTOMER_LOCATED, AppConstants.FAILED, AppConstants.CUSTID_AND_VENDORID_DOES_NOT_EXISTS);
				    	return	new InvalidRequestException("Booking not found for given customer/vendor");
			});
			
			//update Pickup
			bookings.setBookingStatus(AppConstants.CUSTOMER_LOCATED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			
			MyBookings bookingsSaved = null;
			try {
				bookingsSaved = repository.save(bookings);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_CUSTOMER_LOCATED, AppConstants.SUCCESS, null);
			statusHandler.setStatusCode("400");
			statusHandler.setError(AppConstants.SUCCESS);
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			MyBookingsDTO newBookingsDTO = mapper.toDTO(bookingsSaved);
			vendorBookingResponseDTO.setMyBookingsDTO(newBookingsDTO);
		}catch(TokenPersistenceException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_CUSTOMER_LOCATED, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(InvalidRequestException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_CUSTOMER_LOCATED, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(Exception e) {
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_CUSTOMER_LOCATED, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}	
		logger.info("End : update pickup service : " + vendorBooking);

		return vendorBookingResponseDTO;
	}
	
	@Override
	public VendorBookingResponseDTO reachedDropLocation(Long vendorId, Long bookingId, Long custId,
			VendorBookingResponseDTO vendorBooking, StatusHandler statusHandler, String token, String appId) {
		logger.info("Start : update pickup service : " + vendorId + " : " + bookingId + " : " + custId);
		VendorTokens vendorTokens = vendorUtils.validateAccessToken(token);
		VendorBookingResponseDTO vendorBookingResponseDTO = new VendorBookingResponseDTO();
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		
		
		try {
			//Validate Access token
			System.out.println(vendorTokens);
			Optional.ofNullable(vendorTokens.getVendorId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
						AppConstants.UPDATE_REACHED_DROP_LOCATION, AppConstants.FAILED, AppConstants.INVALID_TOKEN);
				return new TokenPersistenceException(AppConstants.INVALID_TOKEN);
			});
			
			//Validate custId and vendorId
			MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(bookingId, custId, vendorId)
				    .orElseThrow(() -> {
				    	TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
								AppConstants.UPDATE_REACHED_DROP_LOCATION, AppConstants.FAILED, AppConstants.CUSTID_AND_VENDORID_DOES_NOT_EXISTS);
				    	return	new InvalidRequestException("Booking not found for given customer/vendor");
			});
			
			//update Pickup
			bookings.setBookingStatus(AppConstants.REACHED_DROP_LOCATION);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			
			MyBookings bookingsSaved = null;
			try {
				bookingsSaved = repository.save(bookings);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_REACHED_DROP_LOCATION, AppConstants.SUCCESS, null);
			statusHandler.setStatusCode("400");
			statusHandler.setError(AppConstants.SUCCESS);
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			MyBookingsDTO newBookingsDTO = mapper.toDTO(bookingsSaved);
			vendorBookingResponseDTO.setMyBookingsDTO(newBookingsDTO);
		}catch(TokenPersistenceException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_REACHED_DROP_LOCATION, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(InvalidRequestException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_REACHED_DROP_LOCATION, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(Exception e) {
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_REACHED_DROP_LOCATION, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}	
		logger.info("End : update pickup service : " + vendorBooking);

		return vendorBookingResponseDTO;
	}
	
	@Override
	public VendorBookingResponseDTO updateDrop(Long vendorId, Long bookingId, Long custId, String otp, VendorBookingResponseDTO vendorBooking,
			StatusHandler statusHandler, String token, String appId) {
		logger.info("Start : update drop service : " + vendorId + " : " + bookingId+" : "+custId);
		
		VendorTokens vendorTokens = vendorUtils.validateAccessToken(token);
		VendorBookingResponseDTO vendorBookingResponseDTO = new VendorBookingResponseDTO();
		// Validate APPID
		System.out.println("APPID: " + appId);
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		
		
		try {
			//Validate Access token
			System.out.println(vendorTokens);
			Optional.ofNullable(vendorTokens.getVendorId()).orElseThrow(() -> {
				TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
						AppConstants.UPDATE_TRUCK_DROP, AppConstants.FAILED, AppConstants.INVALID_TOKEN);
				return new TokenPersistenceException(AppConstants.INVALID_TOKEN);
			});
			
			//Validate custId and vendorId
			MyBookings bookings = repository.findByBookingIdAndCustIdAndVendorId(bookingId, custId, vendorId)
				    .orElseThrow(() -> {
				    	TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
								AppConstants.UPDATE_TRUCK_DROP, AppConstants.FAILED, AppConstants.CUSTID_AND_VENDORID_DOES_NOT_EXISTS);
				    	return	new InvalidRequestException("Booking not found for given customer/vendor");
			});
			
			if(!bookings.getDropOTP().equalsIgnoreCase(otp)) {
				throw new InvalidRequestException(AppConstants.INVALID_OTP);
			}
			
			//update Pickup
			bookings.setBookingStatus(AppConstants.DROP_COMPLETED);
			bookings.setUpdatedAt(LocalDateTime.now());
			bookings.setUpdatedBy(vendorId);
			bookings.setDropOtpIsVerified("Y");
			MyBookings bookingsSaved = null;
			try {
				bookingsSaved = repository.save(bookings);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_DROP, AppConstants.SUCCESS, null);
			statusHandler.setStatusCode("400");
			statusHandler.setError(AppConstants.SUCCESS);
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			MyBookingsDTO newBookingsDTO = mapper.toDTO(bookingsSaved);
			vendorBookingResponseDTO.setMyBookingsDTO(newBookingsDTO);
		}catch(TokenPersistenceException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_DROP, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(InvalidRequestException e) {
			statusHandler.setStatusCode("400");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_DROP, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}catch(Exception e) {
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			TranLogResponse tranlogResponse = transactionUtils.vendorBookingTranLog(vendorTokens,
					AppConstants.UPDATE_TRUCK_DROP, AppConstants.FAILED, e.getMessage());
			vendorBookingResponseDTO.setStatusHandler(statusHandler);
			
		}	
		logger.info("End : update drop service : " + vendorBooking);

		return vendorBookingResponseDTO;
	}

	@Override
	public BookingTransactionResponse createTransaction(BookingTransactionDTO dto, BookingTransactionResponse response,
			StatusHandler statusHandler) {
		logger.info("Start : create transaction service : " + dto);

		logger.info("End : create transaction service : " + dto);
		return response;
	}

	@Override
	public MyBookingsResponse getBookingSummary(BookingSummaryRequest bookingSummary, MyBookingsResponse response,
			StatusHandler statusHandler) {
		logger.info("Start : bokking summary service : " + bookingSummary);

		logger.info("End : booking summary service : " + bookingSummary);
		return response;
	}

	@Override
	public VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorResponse, StatusHandler statusHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
