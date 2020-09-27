package com.bhp.sdk;

import com.bhp.sdk.bean.BhpAccount;
import com.bhp.sdk.constant.BaseConstant;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void bhpAccount_createAccount() {
        BhpAccount bhpAccount = BhpAccount
                .createAccount()
                .build();
        assertNotNull(bhpAccount.getPrivateKey());
        assertNotNull(bhpAccount.getPublicKey());
        assertNotNull(bhpAccount.getAddress());
        assertNotNull(bhpAccount.getBase64PublicKey());
        assertNull(bhpAccount.getMnemonic());
    }
    @Test
    public void bhpAccount_createAccountHasMnemonic() {
        BhpAccount bhpAccount = BhpAccount
                .createAccountHasMnemonic()
                .build();
        assertNotNull(bhpAccount.getMnemonic());
        assertNotNull(bhpAccount.getPrivateKey());
        assertNotNull(bhpAccount.getPublicKey());
        assertNotNull(bhpAccount.getAddress());
        assertNotNull(bhpAccount.getBase64PublicKey());
        System.out.println(bhpAccount);
    }


    @Test
    public void bhpAccount_createAccountWithMnemonic() {
        String mnemonic = "bargain pizza pink feel gentle mean scissors venture easy tilt pull foot neck sentence knife point hand purse rival resemble tunnel cabin order mystery";

        BhpAccount bhpAccount = BhpAccount
                .createAccountFromMnemonic(mnemonic)
                .build();
        assertEquals(bhpAccount.getAddress(),"bhp1qqzlu99rehs48saar8y4ms3zhwgu30rhyhl8cr");
        assertEquals(bhpAccount.getBase64PublicKey(),"Aw2npyKgb/MYA3JEL1nfjZ5nFwCyyOpWS2LRrGPnTWEj");
        assertEquals(bhpAccount.getPrivateKey(),"cb528042aa8ca84a5afef3115a3712fcc7cd147df2105f5e71d565a3c301f8a2");
        assertEquals(bhpAccount.getPublicKey(),"030da7a722a06ff3180372442f59df8d9e671700b2c8ea564b62d1ac63e74d6123");
    }

    @Test
    public void bhpAccount_createAccountWithPrivateKey() {
        String privateKey = "cb528042aa8ca84a5afef3115a3712fcc7cd147df2105f5e71d565a3c301f8a2";
        BhpAccount bhpAccount = BhpAccount
                .createAccountFromPrivateKey(privateKey)
                .build();
        assertEquals(bhpAccount.getBase64PublicKey(),"Aw2npyKgb/MYA3JEL1nfjZ5nFwCyyOpWS2LRrGPnTWEj");
        assertEquals(bhpAccount.getPublicKey(),"030da7a722a06ff3180372442f59df8d9e671700b2c8ea564b62d1ac63e74d6123");
        assertEquals(bhpAccount.getAddress(),"bhp1qqzlu99rehs48saar8y4ms3zhwgu30rhyhl8cr");
    }
}
