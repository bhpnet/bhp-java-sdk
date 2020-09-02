package com.bhp.sdk.bean;

import com.bhp.sdk.Crypto;
import com.bhp.sdk.config.BaseParamConfig;
import com.bhp.sdk.constant.BaseConstant;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author Trigger
 */
public class Account {

    BaseParamConfig baseParamConfig;
    private String privateKey;
    private String publicKey;
    private String mnemonic;
    private String address;
    private String base64PublicKey;
    private String base64PrivateKey;


    public static Builder createAccount() {
        return new Builder();
    }

    public static Builder createAccountWithMnemonic(String mnemonic) {
        if (StringUtils.isBlank(mnemonic)) {
            throw new IllegalArgumentException("mnemonic must not be null");
        }
        Builder builder = new Builder();
        builder.hasMnemonic = true;
        builder.mnemonic = mnemonic;
        return builder;
    }

    public static Builder createAccountWithPrivateKey(String privateKey) {
        if (StringUtils.isBlank(privateKey)) {
            throw new IllegalArgumentException("privateKey must not be null");
        }
        Builder builder = new Builder();
        builder.hasPrivateKey = true;
        builder.privateKey = privateKey;
        return builder;
    }

    @Override
    public String toString() {
        return "Account{" +
                "baseParamConfig=" + baseParamConfig +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                ", address='" + address + '\'' +
                ", base64PublicKey='" + base64PublicKey + '\'' +
                ", base64PrivateKey='" + base64PrivateKey + '\'' +
                '}';
    }

    private Account(Builder builder) {
        this.baseParamConfig = new BaseParamConfig();
        baseParamConfig.setMainPrefix(builder.mainPrefix);
        baseParamConfig.setPath(builder.path);
        baseParamConfig.setPassphrase(builder.passphrase);
        if (builder.hasMnemonic) {

            this.mnemonic = StringUtils.isBlank(builder.mnemonic) ? Crypto.generateMnemonic() : builder.mnemonic;

            this.privateKey = Crypto.generatePrivateKeyFromMnemonic(
                    this.mnemonic,
                    this.baseParamConfig.getPath(),
                    baseParamConfig.getPassphrase());


        } else {
            this.privateKey = builder.hasPrivateKey ? builder.privateKey : Crypto.generatePrivateKey();
        }
        this.publicKey = Crypto.generatePubKeyHexFromPriv(this.privateKey);
        this.address = Crypto.generateAddressFromPub(this.publicKey, baseParamConfig.getMainPrefix());
        if (builder.hasBase64Key) {
            this.base64PrivateKey = Strings.fromByteArray(Base64.encode(Hex.decode(this.privateKey)));
            this.base64PublicKey = Strings.fromByteArray(Base64.encode(Hex.decode(this.publicKey)));
        }

    }

    public String getBase64PublicKey() {
        return base64PublicKey;
    }

    public String getBase64PrivateKey() {
        return base64PrivateKey;
    }

    public String getAddress() {
        return address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public static class Builder {

        private boolean hasMnemonic;
        private boolean hasPrivateKey;
        private String mnemonic;
        private String privateKey;
        private boolean hasBase64Key;
        private String passphrase;
        private String mainPrefix;
        private String path;

        public Builder hasMnemonic(boolean hasMnemonic) {
            this.hasMnemonic = hasMnemonic;
            return this;
        }

        public Builder hasBase64Key(boolean hasBase64Key) {
            this.hasBase64Key = hasBase64Key;
            return this;
        }

        public Builder passPhrase(String passPhrase) {
            this.passphrase = passPhrase;
            return this;
        }

        public Builder mainPrefix(String mainPrefix) {
            this.mainPrefix = mainPrefix;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        protected Builder() {

        }

        public Account build() {
            this.mainPrefix = replaceEmptyParam(this.mainPrefix, BaseConstant.DEFAULT_MAIN_PREFIX);
            this.passphrase = replaceEmptyParam(this.passphrase, BaseConstant.DEFAULT_PASSPHRASE);
            this.path = replaceEmptyParam(this.path, BaseConstant.DEFAULT_PATH);
            return new Account(this);
        }

        private String replaceEmptyParam(String param, String replacement) {
            if (param == null || StringUtils.isBlank(param.trim())) {
                return replacement;
            }
            return param;
        }


    }


}
