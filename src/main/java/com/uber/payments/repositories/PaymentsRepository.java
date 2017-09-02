package com.uber.payments.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uber.payments.entity.Payment;

/**
 * Created by ragiv on 29/07/17.
 */
public interface PaymentsRepository extends CrudRepository<Payment, String> {

}
