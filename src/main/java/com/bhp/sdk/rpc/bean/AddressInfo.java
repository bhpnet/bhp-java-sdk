package com.bhp.sdk.rpc.bean;

import com.alibaba.fastjson.JSONArray;

/**
 * @author Trigger
 */
public class AddressInfo {

    private String address;
    private JSONArray coins;
    private PubKey publicKey;
    private String accountNumber;
    private String sequence;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JSONArray getCoins() {
        return coins;
    }

    public void setCoins(JSONArray coins) {
        this.coins = coins;
    }

    public PubKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PubKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
