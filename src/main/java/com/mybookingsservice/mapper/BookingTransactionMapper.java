package com.mybookingsservice.mapper;

import org.mapstruct.Mapper;

import com.mybookingsservice.domain.BookingTransactionDTO;
import com.mybookingsservice.entity.BookingTransaction;

@Mapper(componentModel = "spring")
public interface BookingTransactionMapper {

	BookingTransaction toEntity(BookingTransactionDTO dto);

	BookingTransactionDTO toDTO(BookingTransaction tran);

}
