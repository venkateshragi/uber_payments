package com.uber.payments.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.uber.payments.entity.IdType;

/**
 * Created by ragiv on 25/06/17.
 */
public class PartnerRegistrationDto {

    String id;

    @NotNull
    String name;

    @NotNull
    String uberUserId;

    @Min(value = 1000000000)
    @Max(value = 9999999999L)
    long phoneNumber;

    @Max(value = 9999999999L)
    long alternatePhoneNumber;

    @NotNull
    String address;

    @NotNull
    IdType idType;

    @NotNull
    String idNumber;

    long dateCreated;

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

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
}
