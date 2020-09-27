package com.bhp.sdk.rpc.bean;

/**
 * @author Trigger
 */
public class Transfer {
    private String type;
    private TransferDetail value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransferDetail getValue() {
        return value;
    }

    public void setValue(TransferDetail value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
