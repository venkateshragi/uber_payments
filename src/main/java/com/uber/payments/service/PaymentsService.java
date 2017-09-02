package com.uber.payments.service;

import java.util.List;

import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.PartnerDebt;

/**
 * Created by ragiv on 25/06/17.
 */
public interface PaymentsService {

    List<PartnerDebt> getPartnerCollectibles();

    void recordPayments(String filePath);
}
