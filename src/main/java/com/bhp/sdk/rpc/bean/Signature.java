package com.bhp.sdk.rpc.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Trigger
 */
public class Signature {

    private PubKey pubKey;
    private String signature;
    @JSONField(name = "account_number")
    private String accountNumber;
    private String sequence;

    public PubKey getPubKey() {
        return pubKey;
    }

    public void setPubKey(PubKey pubKey) {
        this.pubKey = pubKey;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    @Override
    public String toString() {
        return "Signature{" +
                "pubKey=" + pubKey +
                ", signature='" + signature + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}
