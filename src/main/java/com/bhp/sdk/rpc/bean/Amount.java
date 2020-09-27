package com.bhp.sdk.rpc.bean;

/**
 * @author Trigger
 */
public class Amount {
    private String denom;
    private String amount;

    public Amount() {
    }

    public Amount(String denom, String amount) {
        this.denom = denom;
        this.amount = amount;
    }

    public String getDenom() {
        return denom;
    }

    public Amount setDenom(String denom) {
        this.denom = denom;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public Amount setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "denom='" + denom + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
