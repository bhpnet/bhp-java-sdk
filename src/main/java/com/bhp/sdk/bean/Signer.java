package com.bhp.sdk.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bhp.sdk.Crypto;
import com.bhp.sdk.component.Account;
import com.bhp.sdk.rpc.bean.Fee;
import com.bhp.sdk.rpc.bean.PubKey;
import com.bhp.sdk.rpc.bean.Signature;
import org.apache.commons.lang3.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Trigger
 */
public class Signer {


    @JSONField(name = "chain_id")
    private String chainId;
    private Fee fee;
    @JSONField(name = "msgs")
    private JSONArray msg;
    @JSONField(name = "account_number")
    private String accountNumber;
    private String sequence;
    private String memo;
    private String signValue;
    private String privateKey;

    private Signer(Builder builder) {
        this.chainId = builder.chainId;
        this.fee = builder.fee;
        this.msg = builder.msg;
        this.accountNumber = builder.accountNumber;
        this.sequence = builder.sequence;
        this.memo = builder.memo;
        this.privateKey = builder.privateKey;
    }

    public String getChainId() {
        return chainId;
    }

    public Fee getFee() {
        return fee;
    }

    public JSONArray getMsg() {
        return msg;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSequence() {
        return sequence;
    }

    public String getMemo() {
        return memo;
    }

    public static Builder createSigner() {
        return new Builder();
    }


    private Signer sign() throws NoSuchAlgorithmException {
        String value = JSON.toJSONString(this, SerializerFeature.MapSortField);
        System.out.println("排序后待签名的对象:"+value);
        byte[] sign = Crypto.sign(value.getBytes(), this.privateKey);
        this.signValue = Base64.getEncoder().encodeToString(sign);
        return this;
    }

    public Signature signature( Account account) throws NoSuchAlgorithmException {
        Signature signature = new Signature();
        signature.setSequence(this.sequence);
        signature.setAccountNumber(this.accountNumber);
        signature.setSignature(this.signValue);
        PubKey pubKey = new PubKey(PubKey.DEFAULT_TYPE,account.getBase64PublicKey());
        signature.setPubKey(pubKey);
        return signature;
    }

    public String value() {
        return this.signValue;
    }

    public static class Builder {

        private String privateKey;
        private String chainId;
        private Fee fee;
        private JSONArray msg;
        private String accountNumber;
        private String sequence;
        private String memo;

        public Builder chainId(String chainId) {
            this.chainId = chainId;
            return this;
        }

        public Builder fee(Fee fee) {
            this.fee = fee;
            return this;
        }

        public Builder msg(JSONArray msg) {
            this.msg = msg;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder sequence(String sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder memo(String memo) {
            this.memo = memo;
            return this;
        }

        public Builder privateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        protected Builder() {

        }

        public Signer sign() throws NoSuchAlgorithmException {
            if (StringUtils.isBlank(this.privateKey)) {
                throw new IllegalArgumentException("private key must not be null");
            }
            Signer signer = new Signer(this);
            return signer.sign();
        }

    }
}