package com.bhp.sdk.rpc.bean;

import com.alibaba.fastjson.JSONArray;

/**
 * @author Trigger
 */
public class TransferDetail {

    private JSONArray msg;
    private Fee fee;
    private String memo;
    private Signature signature;

    @Override
    public String toString() {
        return "TransferDetail{" +
                "msg=" + msg +
                ", fee=" + fee +
                ", memo='" + memo + '\'' +
                ", signature=" + signature +
                '}';
    }

    public JSONArray getMsg() {
        return msg;
    }

    public void setMsg(JSONArray msg) {
        this.msg = msg;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }
}
