package com.uber.payments.repositories.vo;

public class PartnerPaymentInfo {

    String partnerId;

    double shortfall;

    int totalPayments;

    public PartnerPaymentInfo() {
    }

    public PartnerPaymentInfo(String partnerId, double shortfall, int totalPayments) {
        this.partnerId = partnerId;
        this.shortfall = shortfall;
        this.totalPayments = totalPayments;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public double getShortfall() {
        return shortfall;
    }

    public void setShortfall(double shortfall) {
        this.shortfall = shortfall;
    }

    public int getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
    }
}
