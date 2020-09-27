package com.bhp.sdk.crypto;

/**
 * @author Trigger
 */
public interface KeyPairGenerator {

    String genPrivateKey();

    String genPrivateKeyFromMnemonic(String mnemonic);

    String genPubKeyFromPrivateKey(String privateKey);

    KeyPair genKeyPair();

    KeyPair genKeyPair(String mnemonic);

    KeyPair genKeyPairFromPrivateKey(String privateKey);

    void initialize();

}
