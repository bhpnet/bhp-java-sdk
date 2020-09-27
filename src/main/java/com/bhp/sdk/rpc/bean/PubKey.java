package com.bhp.sdk.rpc.bean;

/**
 * @author Trigger
 */
public class PubKey {
    public static final String DEFAULT_TYPE = "tendermint/PubKeySecp256k1";
    String type;
    String value;

    public PubKey(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public PubKey() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
