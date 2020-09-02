package com.bhp.sdk;

import com.bhp.sdk.bean.Account;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void createAccount() {
        Account account = Account
                .createAccount()
                .hasMnemonic(true)
                .hasBase64Key(true)
                .build();
        System.out.println(account);
    }


    @Test
    public void createAccountWithMnemonic() {
        String mnemonic = "bargain pizza pink feel gentle mean scissors venture easy tilt pull foot neck sentence knife point hand purse rival resemble tunnel cabin order mystery";

        Account account = Account
                .createAccountWithMnemonic(mnemonic)
                .hasBase64Key(true)
                .build();
        assertEquals(account.getAddress(),"bhp1qqzlu99rehs48saar8y4ms3zhwgu30rhyhl8cr");
        assertEquals(account.getBase64PrivateKey(),"y1KAQqqMqEpa/vMRWjcS/MfNFH3yEF9ecdVlo8MB+KI=");
        assertEquals(account.getBase64PublicKey(),"Aw2npyKgb/MYA3JEL1nfjZ5nFwCyyOpWS2LRrGPnTWEj");
        assertEquals(account.getPrivateKey(),"cb528042aa8ca84a5afef3115a3712fcc7cd147df2105f5e71d565a3c301f8a2");
        assertEquals(account.getPublicKey(),"030da7a722a06ff3180372442f59df8d9e671700b2c8ea564b62d1ac63e74d6123");
    }

    @Test
    public void createAccountWithPrivateKey() {
        String privateKey = "cb528042aa8ca84a5afef3115a3712fcc7cd147df2105f5e71d565a3c301f8a2";
        Account account = Account
                .createAccountWithPrivateKey(privateKey)
                .build();
        assertEquals(account.getAddress(),"bhp1qqzlu99rehs48saar8y4ms3zhwgu30rhyhl8cr");
        assertEquals(account.getBase64PrivateKey(),"y1KAQqqMqEpa/vMRWjcS/MfNFH3yEF9ecdVlo8MB+KI=");
        assertEquals(account.getBase64PublicKey(),"Aw2npyKgb/MYA3JEL1nfjZ5nFwCyyOpWS2LRrGPnTWEj");
        assertEquals(account.getPublicKey(),"030da7a722a06ff3180372442f59df8d9e671700b2c8ea564b62d1ac63e74d6123");
    }
}
