package com.bhp.sdk.crypto;

/**
 * @author Trigger
 */
public class KeyPair {

    private String privateKey;
    private String publicKey;

    public KeyPair(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }
    public String getPrivateKey() {
        return this.privateKey;
    }




}
