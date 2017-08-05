package com.uber.payments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
//    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "DATE_CREATED")
    Date dateCreated;

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
}
