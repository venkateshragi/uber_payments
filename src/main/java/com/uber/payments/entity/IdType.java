package com.uber.payments.entity;

/**
 * Created by ragiv on 25/06/17.
 */
public enum IdType {

    LICENSE("License"),
    PASSPORT("Passport"),
    AADHAR("Aadhar");


    String value;

    IdType(String value) {
        this.value = value;
    }

    public boolean isEquals(Object other) {
        return this.value.equals(((IdType)other).value);
    }

    @Override
    public String toString() {
        return value;
    }
}
