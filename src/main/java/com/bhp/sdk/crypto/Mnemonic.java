package com.bhp.sdk.crypto;

import com.bhp.sdk.constant.BaseConstant;
import com.bhp.sdk.exception.KeyPairCreateException;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author Trigger
 */
public class Mnemonic {

    public static String generator(BaseConstant.WordLength wordLength) {
        if (wordLength == null) {
            wordLength = BaseConstant.WordLength.TWENTY_FOUR;
        }
        byte[] entropy = new byte[wordLength.getLength()];
        new SecureRandom().nextBytes(entropy);
        try {
            return Utils.join(MnemonicCode.INSTANCE.toMnemonic(entropy));
        } catch (MnemonicException.MnemonicLengthException e) {
            e.printStackTrace();
            return null;
        }
    }
}
