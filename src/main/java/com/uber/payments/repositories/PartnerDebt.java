package com.uber.payments.repositories;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by ragiv on 06/08/17.
 */
public interface PartnerDebt {

    @Value("#{target.id}")
    String getPartnerId();

    String getUberUserId();

    @Value("#{ (target.amountDue <target.downPayment) ? target.amountDue : target.downPayment }")
    double getAmountToBeCollected();
}
