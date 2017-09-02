package com.uber.payments.repositories;

import org.springframework.beans.factory.annotation.Value;

public interface PartnerWithoutUberId {

    @Value("#{target.id}")
    String getPartnerId();

    String getName();

    @Value("#{target.phoneNumber} , #{target.alternatePhoneNumber}")
    String getTelephoneNumbers();

    String getVehicleNumber();

}
