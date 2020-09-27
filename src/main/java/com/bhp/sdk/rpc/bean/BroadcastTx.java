package com.bhp.sdk.rpc.bean;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @author Trigger
 */
public class BroadcastTx {
    private JSONArray msg;
    private Fee fee;
    private String memo;
    private List<Signature> signatures;

    public BroadcastTx(Transfer transfer,List<Signature> signature) {
        this.msg = transfer.getValue().getMsg();
        this.fee = transfer.getValue().getFee();
        this.memo = transfer.getValue().getMemo();
        this.signatures = signature;
    }

    public BroadcastTx() {
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

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }
}
