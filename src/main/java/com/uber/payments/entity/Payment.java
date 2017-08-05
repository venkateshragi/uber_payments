package com.uber.payments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    String id;

    @ManyToOne
    @JoinColumn(name = "PARTNER_ID", nullable = false)
    Partner partner;

    @Column(name = "UBER_USER_ID")
    String uberUserId;

    @Column(name = "AMOUNT_TO_BE_CHARGED")
    double amountToBeCharged;

    @Column(name = "AMOUNT_PAID")
    double amountPaid;

    @Column(name = "SHORTFALL")
    double shortfall;

    @Column(name = "DATE_CREATED")
    Date dateCreated;

    @Column(name = "NUMBER_OF_ASSETS")
    int numberOfAssets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getUberUserId() {
        return uberUserId;
    }

    public void setUberUserId(String uberUserId) {
        this.uberUserId = uberUserId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getShortfall() {
        return shortfall;
    }

    public void setShortfall(double shortfall) {
        this.shortfall = shortfall;
    }

    public double getAmountToBeCharged() {
        return amountToBeCharged;
    }

    public void setAmountToBeCharged(double amountToBeCharged) {
        this.amountToBeCharged = amountToBeCharged;
    }

    public int getNumberOfAssets() {
        return numberOfAssets;
    }

    public void setNumberOfAssets(int numberOfAssets) {
        this.numberOfAssets = numberOfAssets;
    }
}
