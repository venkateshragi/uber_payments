package com.uber.payments.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by ragiv on 25/06/17.
 */
@Entity
@Table(name = "ASSET_PURCHASED")
public class AssetPurchased {

    public enum AssetType {
        MOBILE("Mobile"),
        TAB("Tab");

        String value;

        AssetType(String value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    String id;

    @Column(name = "ASSET_NAME")
    String assetName;

    @ManyToOne
    @JoinColumn(name = "PARTNER_ID", nullable = false)
    Partner partner;

    @Column(name = "ASSET_TYPE")
    @Enumerated(EnumType.STRING)
    AssetType assetType;

    @Column(name = "PURCHASE_AMOUNT")
    double purchaseAmount;

    @Column(name = "DOWN_PAYMENT")
    double downPayment;

    @Column(name = "NO_OF_EMW")
    int noOfEWI;

    @Column(name = "EWI")
    double ewi;

    @Column(name = "DATE_CREATED")
    Date dateCreated;

    @Column(name = "AMOUNT_DUE")
    double amountDue;

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

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
