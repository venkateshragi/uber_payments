package com.uber.payments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ragiv on 25/06/17.
 */
@Entity
@Table(name = "ASSET_PURCHASED")
public class AssetPurchased {

    enum AssetType {
        MOBILE("mobile"),
        TAB("Tab");

        String value;

        AssetType(String value) {
            this.value = value;
        }
    }

    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "ASSET_NAME")
    String assetName;

    @Column(name = "PARTNER_ID")
    String partnerId;

    @Column(name = "ASSET_TYPE")
    AssetType assetType;

    @Column(name = "PURCHASE_AMOUNT")
    double purchaseAmount;

    @Column(name = "DOWN_PAYMENT")
    double downPayment;

    @Column(name = "NO_OF_EMW")
    int noOfEWI;

    @Column(name = "EWI")
    int ewi;

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

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
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

    public void setDownPayment(double downpayment) {
        this.downPayment = downpayment;
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
}
