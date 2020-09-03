package com.bhp.sdk.bean;

import java.util.List;

/**
 * @author Trigger
 */
public class Fee {

    private String gas;
    private List<Amount> amount;

    public Fee() {
    }

    public Fee(String gas, List<Amount> amount) {
        this.gas = gas;
        this.amount = amount;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public List<Amount> getAmount() {
        return amount;
    }

    public void setAmount(List<Amount> amount) {
        this.amount = amount;
    }
}
