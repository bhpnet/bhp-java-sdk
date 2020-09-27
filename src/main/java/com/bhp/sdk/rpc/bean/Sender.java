package com.bhp.sdk.rpc.bean;

import java.util.List;

/**
 * @author Trigger
 */
public class Sender {

    private TxInformation base_req;
    private List<Amount> amount;

    public TxInformation getBase_req() {
        return base_req;
    }

    public void setBase_req(TxInformation base_req) {
        this.base_req = base_req;
    }

    public List<Amount> getAmount() {
        return amount;
    }

    public Sender setAmount(List<Amount> amount) {
        this.amount = amount;
        return this;
    }
}
