package com.uber.payments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uber.payments.entity.AssetPurchased;

/**
 * Created by ragiv on 24/07/17.
 */
public interface AssetPurchasedRepository extends CrudRepository<AssetPurchased, String> {

    List<AssetPurchased> findByPartner_IdOrderByDateCreatedAsc(String id);

    @Query(value = "select ap.partner.id as partnerId, ap.partner.uberUserId as uberUserId, " +
            "count(*) as numberOfAssets, sum(ap.amountDue) as amountToBeCollected from AssetPurchased ap " +
            "where ap.amountDue > 0 group by ap.partner")
    List<PartnerCollectibles> getPartnerCollectibles();

    interface PartnerCollectibles {
        String getPartnerId();
        String getUberUserId();
        int getNumberOfAssets();
        double getAmountToBeCollected();
    }

}
