package com.uber.payments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by ragiv on 25/06/17.
 */
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @ManyToOne()
    @JoinColumn(name = "PARTNER_ID")
    Partner partner;

    @Column(name = "UBER_USER_ID")
    String uberUserId;

    @Column(name = "AMOUNT_PAID")
    double amountPaid;

    @Column(name = "SHORTFALL")
    double shortfall;

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
}
