package com.uber.payments.service;

import java.util.List;

import com.uber.payments.repositories.vo.PartnerDebt;

/**
 * Created by ragiv on 25/06/17.
 */
public interface PaymentsService {

    List<PartnerDebt> getPartnerCollectibles();

    void recordPayments(String filePath);
}
