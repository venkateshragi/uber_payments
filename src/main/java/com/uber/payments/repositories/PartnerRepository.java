package com.uber.payments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.uber.payments.entity.Partner;

/**
 * Created by ragiv on 16/07/17.
 */
@Component
public interface PartnerRepository extends JpaRepository<Partner, String> {

    List<PartnerDebt> findByAmountDueGreaterThan(double value);

    List<PartnerWithoutUberId> findByUberUserIdIsNull();

}
