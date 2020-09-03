package com.bhp.sdk.bean;

/**
 * @author Trigger
 */
public class Amount {
    private String denom;
    private String amount;

    public Amount(String denom, String amount) {
        this.denom = denom;
        this.amount = amount;
    }

    public Amount() {
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
