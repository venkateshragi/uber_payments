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

    /**
     * returns the latest payment information along with the number of times a partner was asked for payment.
     * @param partnerIds
     * @return
     */
    @Query(nativeQuery = true,
            value = "select p.PARTNER_ID, p.SHORTFALL, count(*) as totalPayments" +
                    " from PAYMENT p where p.PARTNER_ID in :partnerIds " +
                    " group by p.PARTNER_ID having p.DATE_CREATED = max(p.DATE_CREATED)")
    List<PartnerPaymentInfo> findPreviousShortfall(@Param("partnerIds") List<String> partnerIds);
}
