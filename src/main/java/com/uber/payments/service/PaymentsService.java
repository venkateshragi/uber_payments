package com.uber.payments.service;

import java.util.List;

import com.uber.payments.dto.AssetPurchasedDto;
import com.uber.payments.dto.PartnerRegistrationDto;
import com.uber.payments.entity.AssetPurchased;
import com.uber.payments.entity.Partner;
import com.uber.payments.repositories.AssetPurchasedRepository;

/**
 * Created by ragiv on 25/06/17.
 */
public interface PaymentsService {

    Partner createPartner(PartnerRegistrationDto partner);

    AssetPurchased createAssetPurchased(AssetPurchasedDto assetPurchasedDto);

    List<AssetPurchasedRepository.PartnerCollectibles> getPartnerCollectibles();

    void recordPayments(String filePath);
}
