package com.bhp.sdk.crypto;

import com.bhp.sdk.constant.BaseConstant;
import com.bhp.sdk.exception.KeyPairCreateException;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.*;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author Trigger
 */
public class BhpKeyPairGenerator implements KeyPairGenerator {

    private String passphrase;
    private Path path;


    public BhpKeyPairGenerator() {
        initialize();
    }


    @Override
    public String genPrivateKey() {
        SecureRandom rng = new SecureRandom();
        byte[] randomBytes = new byte[32];
        rng.nextBytes(randomBytes);
        return Hex.toHexString(randomBytes);
    }


    @Override
    public String genPrivateKeyFromMnemonic(String mnemonic) {
        if (StringUtils.isBlank(mnemonic)) {
            throw new KeyPairCreateException("BhpKeyPairGenerator.genPrivateKeyFromMnemonic error mnemonic must not be null");
        }
        List<String> words = Splitter.on(" ").splitToList(mnemonic);
        byte[] seed = MnemonicCode.toSeed(words, passphrase);
        DeterministicKey key = HDKeyDerivation.createMasterPrivateKey(seed);
        List<ChildNumber> childNumbers = HDUtils.parsePath(this.path.getPath());
        for (ChildNumber cn : childNumbers) {
            key = HDKeyDerivation.deriveChildKey(key, cn);
        }
        return key.getPrivateKeyAsHex();
    }

    @Override
    public String genPubKeyFromPrivateKey(String privateKey) {
        if (StringUtils.isBlank(privateKey)) {
            throw new KeyPairCreateException("BhpKeyPairGenerator.genPubKeyFromPrivateKey error privateKey must not be null");
        }
        ECKey k = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        return k.getPublicKeyAsHex();
    }

    @Override
    public void initialize() {
        initialize(BaseConstant.DEFAULT_PASSPHRASE, new Path(547));
    }

    public void initialize(String passphrase, Path path) {
        this.passphrase = passphrase;
        this.path = path;
    }

    @Override
    public KeyPair genKeyPair() {
        return genKeyPair(null);
    }
    @Override
    public KeyPair genKeyPairFromPrivateKey(String privateKey) {
        if (StringUtils.isBlank(privateKey)) {
            privateKey = genPrivateKey();
        }
        return new KeyPair(privateKey, genPubKeyFromPrivateKey(privateKey));
    }

    @Override
    public KeyPair genKeyPair(String mnemonic) {
        String privateKey;
        if (StringUtils.isBlank(mnemonic)) {
            privateKey = genPrivateKey();
        } else {
            privateKey = genPrivateKeyFromMnemonic(mnemonic);
        }
        return genKeyPairFromPrivateKey(privateKey);
    }
}
