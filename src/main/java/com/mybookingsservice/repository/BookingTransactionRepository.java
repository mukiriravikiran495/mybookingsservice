package com.mybookingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.BookingTransaction;

@Repository
public interface BookingTransactionRepository extends JpaRepository<BookingTransaction, Long>{

}
