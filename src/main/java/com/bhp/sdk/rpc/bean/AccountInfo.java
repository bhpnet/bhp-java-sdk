package com.bhp.sdk.rpc.bean;

/**
 * @author Trigger
 */
public class AccountInfo {

    private String type;
    private AddressInfo value;
    private Long height;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddressInfo getValue() {
        return value;
    }

    public void setValue(AddressInfo value) {
        this.value = value;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", height=" + height +
                '}';
    }
}
