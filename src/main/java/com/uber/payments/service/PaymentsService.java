package com.uber.payments.service;

import java.util.List;

import com.uber.payments.repositories.vo.PartnerDebt;

/**
 * Created by ragiv on 25/06/17.
 */
public interface PaymentsService {

    /**
     * Returns a list of collectibles from partner.
     * @return a list of partnerDebt describing the collectibles from partners.
     */
    List<PartnerDebt> getPartnerCollectibles();

    /**
     * Records payments of the partners.
     * @param filePath Path to the location of file containing payments.
     */
    void recordPayments(String filePath);
}
