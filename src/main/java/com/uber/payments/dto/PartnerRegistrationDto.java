package com.uber.payments.dto;

import com.uber.payments.entity.IdType;

/**
 * Created by ragiv on 25/06/17.
 */
public class PartnerRegistrationDto {

    String name;

    String uberUserId;

    String lenderContractId;

    long phoneNumber;

    long alternatePhoneNumber;

    IdType idType;

    String idNumber;
}
