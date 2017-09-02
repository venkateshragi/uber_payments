package com.uber.payments.service;

import java.util.List;

import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.PartnerWithoutUberId;

public interface PartnerService {

    Partner createPartner(PartnerRegistrationDto partner);

    List<PartnerWithoutUberId> findPartnersWithoutUberId();
}