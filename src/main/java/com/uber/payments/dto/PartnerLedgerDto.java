package com.uber.payments.dto;

/**
 * Created by ragiv on 30/07/17.
 */
public class PartnerLedgerDto {

    private String partnerId;

    private String uberUserId;

    private double amountToBeCollected;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUberUserId() {
        return uberUserId;
    }

    public void setUberUserId(String uberUserId) {
        this.uberUserId = uberUserId;
    }

    public double getAmountToBeCollected() {
        return amountToBeCollected;
    }

    public void setAmountToBeCollected(double amountToBeCollected) {
        this.amountToBeCollected = amountToBeCollected;
    }
}
