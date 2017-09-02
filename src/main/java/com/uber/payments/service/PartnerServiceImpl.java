package com.uber.payments.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.PartnerRepository;
import com.uber.payments.repositories.PartnerWithoutUberId;

@Service
public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;

    @Override
    public Partner createPartner(PartnerRegistrationDto partnerDto) {
        Partner partner = new Partner();
        partner.setName(partnerDto.getName());
        partner.setPhoneNumber(partnerDto.getPhoneNumber());
        partner.setAlternatePhoneNumber(partnerDto.getAlternatePhoneNumber());
        partner.setAddress(partnerDto.getAddress());
        partner.setIdType(partnerDto.getIdType());
        partner.setIdNumber(partnerDto.getIdNumber());
        partner.setVehicleNumber(partnerDto.getVehicleNumber());

        partner.setAssetName(partnerDto.getAssetName());
        partner.setAssetType(partnerDto.getAssetType());
        partner.setPurchaseAmount(partnerDto.getPurchaseAmount());
        partner.setNoOfEWI(partnerDto.getNoOfEWI());
        partner.setEwi(partnerDto.getEwi());
        partner.setDownPayment(partnerDto.getDownPayment());
        partner.setAmountDue(partnerDto.getPurchaseAmount() - partnerDto.getDownPayment());

        if (partnerDto.getDatePurchased() == 0)
            partner.setDatePurchased(new Date());
        else
            partner.setDatePurchased(new Date(partnerDto.getDatePurchased()));

        return partnerRepository.save(partner);
    }

    @Override
    public List<PartnerWithoutUberId> findPartnersWithoutUberId() {
        return partnerRepository.findByUberUserIdIsNull();
    }
}
