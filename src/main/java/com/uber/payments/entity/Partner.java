package com.uber.payments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ragiv on 25/06/17.
 */
@Entity
@Table(name = "PARTNER")
public class Partner {

    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "UBER_USER_ID")
    String uberUserId;

    @Column(name = "LENDER_CONTRACT_ID")
    String lenderContractId;

    @Column(name = "PHONE_NUMBER")
    long phoneNumber;

    @Column(name = "ALTERNATE_PHONE_NUMBER")
    long alternatePhoneNumber;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "ID_TYPE")
    IdType idType;

    @Column(name = "ID_NUMBER")
    String idNumber;

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

    public String getLenderContractId() {
        return lenderContractId;
    }

    public void setLenderContractId(String lenderContractId) {
        this.lenderContractId = lenderContractId;
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
}
