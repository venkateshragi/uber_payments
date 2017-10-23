package com.uber.payments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.vo.PartnerDebt;
import com.uber.payments.repositories.vo.PartnerWithoutUberId;

/**
 * Created by ragiv on 16/07/17.
 */
@Component
public interface PartnerRepository extends JpaRepository<Partner, String> {

    List<PartnerDebt> findByAmountDueGreaterThan(double value);

    List<PartnerWithoutUberId> findByUberUserIdIsNull();

    @Modifying
    @Query("update Partner p set p.uberUserId = :uberId where p.id = :partnerId")
    int setUberIdForPartner(@Param("partnerId") String partnerId, @Param("uberId") String uberId);

}
