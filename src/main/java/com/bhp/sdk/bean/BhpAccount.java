package com.bhp.sdk.bean;

import com.bhp.sdk.component.Account;
import com.bhp.sdk.constant.BaseConstant;
import com.bhp.sdk.crypto.BhpKeyPairGenerator;
import com.bhp.sdk.crypto.KeyPair;
import com.bhp.sdk.crypto.KeyPairGenerator;
import com.bhp.sdk.crypto.Mnemonic;
import com.bhp.sdk.utils.AddressUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.Optional;

/**
 * @author Trigger
 */
public class BhpAccount implements Account {


    private String privateKey;
    private String publicKey;
    private String mnemonic;
    private String address;
    private String base64PublicKey;


    public static Builder createAccount() {
        return new Builder(new BhpKeyPairGenerator());
    }

    public static Builder createAccountHasMnemonic(BaseConstant.WordLength wordLength) {
        Builder builder = new Builder(new BhpKeyPairGenerator());
        builder.hasMnemonic = true;
        builder.wordLength = Optional.ofNullable(wordLength).orElse(BaseConstant.WordLength.TWENTY_FOUR);
        return builder;
    }
    public static Builder createAccountHasMnemonic() {
        return createAccountHasMnemonic(null);
    }

    public static Builder createAccountFromMnemonic(String mnemonic) {
        if (StringUtils.isBlank(mnemonic)) {
            throw new IllegalArgumentException("mnemonic must not be null");
        }
        Builder builder = new Builder(new BhpKeyPairGenerator());
        builder.hasMnemonic = true;
        builder.mnemonic = mnemonic;
        return builder;
    }

    public static Builder createAccountFromPrivateKey(String privateKey) {
        if (StringUtils.isBlank(privateKey)) {
            throw new IllegalArgumentException("privateKey must not be null");
        }
        Builder builder = new Builder(new BhpKeyPairGenerator());
        builder.hasMnemonic = false;
        builder.privateKey = privateKey;
        return builder;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                ", address='" + address + '\'' +
                ", base64PublicKey='" + base64PublicKey + '\'' +
                '}';
    }

    private BhpAccount(Builder builder) {
        if (builder.hasMnemonic) {
            this.mnemonic = builder.mnemonic;
        }
        this.privateKey = builder.keyPair.getPrivateKey();
        this.publicKey = builder.keyPair.getPublicKey();
        this.address = builder.address;
        this.base64PublicKey = Strings.fromByteArray(Base64.encode(Hex.decode(this.publicKey)));
    }

    @Override
    public String getBase64PublicKey() {
        return base64PublicKey;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPrivateKey() {
        return privateKey;
    }

    @Override
    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public String getMnemonic() {
        return mnemonic;
    }

    public static class Builder {

        private KeyPair keyPair;
        private KeyPairGenerator keyPairGenerator;
        private String privateKey;
        private String address;
        private String mnemonic;
        private boolean hasMnemonic;
        private BaseConstant.WordLength wordLength;

        private Builder(KeyPairGenerator keyPairGenerator) {
            this.keyPairGenerator = keyPairGenerator;
        }

        public BhpAccount build() {
            if (hasMnemonic) {
                this.mnemonic = StringUtils.isBlank(mnemonic) ? Mnemonic.generator(wordLength) : mnemonic;
                this.keyPair = keyPairGenerator.genKeyPair(mnemonic);
            } else {
                if (StringUtils.isNotBlank(this.privateKey)) {
                    this.keyPair = keyPairGenerator.genKeyPairFromPrivateKey(this.privateKey);
                } else {
                    this.keyPair = keyPairGenerator.genKeyPair();
                }

            }
            this.address = AddressUtil.createNewAddressSecp256k1(BaseConstant.DEFAULT_BHP_PREFIX, Hex.decode(this.keyPair.getPublicKey()));
            return new BhpAccount(this);
        }
    }


}
