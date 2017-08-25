package com.uber.payments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by ragiv on 25/06/17.
 */
@Entity
@Table(name = "PARTNER")
public class Partner {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    String id;

    @Column(name = "NAME")
    String name;

    @Column(name = "UBER_USER_ID")
    String uberUserId;

    @Column(name = "PHONE_NUMBER")
    long phoneNumber;

    @Column(name = "ALTERNATE_PHONE_NUMBER")
    long alternatePhoneNumber;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "ID_TYPE")
    @Enumerated(EnumType.STRING)
    IdType idType;

    @Column(name = "ID_NUMBER")
    String idNumber;

    @Column(name = "VEHICLE_NUMBER")
    String vehicleNumber;

    @Column(name = "ASSET_NAME")
    String assetName;

    @Column(name = "ASSET_TYPE")
    @Enumerated(EnumType.STRING)
    AssetType assetType;

    @Column(name = "PURCHASE_AMOUNT")
    double purchaseAmount;

    @Column(name = "DOWN_PAYMENT")
    double downPayment;

    @Column(name = "NO_OF_EWI")
    int noOfEWI;

    @Column(name = "EWI")
    double ewi;

    @Column(name = "DATE_PURCHASED")
    Date datePurchased;

    @Column(name = "AMOUNT_DUE")
    double amountDue;

    public Partner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Partner(String id) {
        this.id = id;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
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

    public double getEwi() {
        return ewi;
    }

    public void setEwi(double ewi) {
        this.ewi = ewi;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}
