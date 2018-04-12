package com.uber.payments.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.uber.payments.entity.AssetType;
import com.uber.payments.entity.IdType;

/**
 * Created by ragiv on 25/06/17.
 */
public class PartnerRegistrationDto {

    String id;

    @NotNull
    String name;

    String uberUserId;

    @Min(value = 1000000000)
    @Max(value = 9999999999L)
    long phoneNumber;

    long alternatePhoneNumber;

    String address;

    @NotNull
    IdType idType;

    @NotNull
    String idNumber;

    @NotNull
    String vehicleNumber;

    @NotNull
    String assetName;

    @NotNull
    AssetType assetType;

    @Min(value = 1)
    double purchaseAmount;

    @NotNull
    Double downPayment;

    @NotNull
    Integer noOfEWI;

//    double ewi;

    double amountDue;

    long datePurchased;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUberUserId() {
        return uberUserId;
    }

    public void setUberUserId(String uberUserId) {
        this.uberUserId = uberUserId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(long alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
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

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public int getNoOfEWI() {
        return noOfEWI;
    }

    public void setNoOfEWI(int noOfEWI) {
        this.noOfEWI = noOfEWI;
    }

    /*public double getEwi() {
        return ewi;
    }

    public void setEwi(double ewi) {
        this.ewi = ewi;
    }*/

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public long getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(long datePurchased) {
        this.datePurchased = datePurchased;
    }
}
