package com.uber.payments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uber.payments.entity.Payment;
import com.uber.payments.repositories.vo.PartnerPaymentInfo;

/**
 * Created by ragiv on 29/07/17.
 */
public interface PaymentsRepository extends CrudRepository<Payment, String> {

    @Query(nativeQuery = true,
            value = "select com.uber.payments.repositories.vo.PartnerPaymentInfo(PARTNER_ID, SHORTFALL, count(*))" +
                    " from PAYMENT group by PARTNER_ID having DATE_CREATED = max(DATE_CREATED)" +
                    " where PARTNER_ID in :partnerIds")
    List<PartnerPaymentInfo> findPreviousShortfall(@Param("partnerIds") List<String> partnerIds);
}
