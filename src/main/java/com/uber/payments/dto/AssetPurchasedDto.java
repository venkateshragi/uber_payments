package com.uber.payments.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.uber.payments.entity.AssetPurchased;
import com.uber.payments.entity.Partner;

/**
 * Created by ragiv on 24/07/17.
 */
public class AssetPurchasedDto {

    String id;

    @NotNull
    String assetName;

    @NotNull
    String partnerId;

    @NotNull
    AssetPurchased.AssetType assetType;

    @Min(value = 1)
    double purchaseAmount;

    @Min(value = 1)
    double downPayment;

    int noOfEWI;

    int ewi;

    long dateCreated;

    double amountDue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public AssetPurchased.AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetPurchased.AssetType assetType) {
        this.assetType = assetType;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public int getNoOfEWI() {
        return noOfEWI;
    }

    public void setNoOfEWI(int noOfEWI) {
        this.noOfEWI = noOfEWI;
    }

    public int getEwi() {
        return ewi;
    }

    public void setEwi(int ewi) {
        this.ewi = ewi;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}
