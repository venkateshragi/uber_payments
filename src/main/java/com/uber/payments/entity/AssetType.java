package com.uber.payments.entity;

/**
 * Created by ragiv on 05/08/17.
 */
public enum AssetType {

    MOBILE("Mobile"),
    TAB("Tab");

    String value;

    AssetType(String value) {
        this.value = value;
    }
}
