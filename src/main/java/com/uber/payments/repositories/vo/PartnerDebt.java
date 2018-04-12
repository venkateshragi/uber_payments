package com.uber.payments.repositories.vo;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by ragiv on 06/08/17.
 */
public class PartnerDebt {

    public String partnerId;

    public String uberUserId;

    public int noOfEWI;

    public double amountToBeCollected;

    public boolean isNPA;

    public PartnerDebt(String id, String uberUserId, int noOfEWI, double amountDue, double ewi) {
        this.partnerId = id;
        this.uberUserId = uberUserId;
        this.noOfEWI = noOfEWI;
        this.amountToBeCollected = amountDue < ewi ? amountDue : ewi;
    }

//    @Value("#{target.id}")
    public String getPartnerId(){
        return this.partnerId;
    }

    public String getUberUserId(){
        return this.uberUserId;
    }

//    int getNoOfEWI();

//    @Value("#{ (target.amountDue < target.downPayment) ? target.amountDue : target.downPayment }")
    public double getAmountToBeCollected(){
        return this.amountToBeCollected;
    }
}
