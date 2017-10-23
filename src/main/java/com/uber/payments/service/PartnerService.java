package com.uber.payments.service;

import java.util.List;

import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.vo.PartnerWithoutUberId;

public interface PartnerService {

    /**
     * Registers and creates a new partner records.
     * @param partner
     * @return partner object
     */
    Partner createPartner(PartnerRegistrationDto partner);

    /**
     * Finds partners without uber id.
     * @return list of partners without uber id mapping
     */
    List<PartnerWithoutUberId> findPartnersWithoutUberId();

    /**
     * updates the uber ids of the partners contained in the file.
     * @param filePath
     */
    void updateUberIds(String filePath);
}
