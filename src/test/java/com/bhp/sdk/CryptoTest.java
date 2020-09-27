package com.bhp.sdk;

import com.bhp.sdk.constant.BaseConstant;
import com.bhp.sdk.crypto.BhpKeyPairGenerator;
import com.bhp.sdk.crypto.KeyPairGenerator;
import com.bhp.sdk.crypto.Mnemonic;
import org.junit.Test;
import static org.junit.Assert.*;


public class CryptoTest {

    @Test
    public void mnemonic_generator_null() {
        String generator = Mnemonic.generator(null);
        System.out.println(generator);
        assertNotNull(generator);
    }
    @Test
    public void mnemonic_generator_24() {
        String generator = Mnemonic.generator(BaseConstant.WordLength.TWENTY_FOUR);
        System.out.println(generator);
        assertEquals(24,generator.split(" ").length);
    }
    @Test
    public void mnemonic_generator_12() {
        String generator = Mnemonic.generator(BaseConstant.WordLength.TWELVE);
        System.out.println(generator);
        assertEquals(12,generator.split(" ").length);
    }
    @Test
    public void keyPairGenerator_getPrivateKeyFromMnemonic_24Length() {
        KeyPairGenerator keyPairGenerator = new BhpKeyPairGenerator();
        String privateKey = keyPairGenerator.genPrivateKeyFromMnemonic("bargain pizza pink feel gentle mean scissors venture easy tilt pull foot neck sentence knife point hand purse rival resemble tunnel cabin order mystery");
        assertEquals(privateKey,"cb528042aa8ca84a5afef3115a3712fcc7cd147df2105f5e71d565a3c301f8a2");
        String publicKey = keyPairGenerator.genPubKeyFromPrivateKey(privateKey);
        assertEquals(publicKey,"030da7a722a06ff3180372442f59df8d9e671700b2c8ea564b62d1ac63e74d6123");
    }
    @Test
    public void keyPairGenerator_getPrivateKeyFromMnemonic_12Length() {
        long start = System.currentTimeMillis();
        KeyPairGenerator keyPairGenerator = new BhpKeyPairGenerator();
        String privateKey = keyPairGenerator.genPrivateKeyFromMnemonic("wing century wish drip rifle sausage floor end win trip approve leaf");
        String publicKey = keyPairGenerator.genPubKeyFromPrivateKey(privateKey);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " miles");
    }




}
