package com.bhp.sdk.crypto;

/**
 * @author Trigger
 */
public class Path {

    private static final String M = "M";
    private static final String PURPOSE = "44H";
    private int coinType;
    private int account;
    private int change;
    private int addressIndex;

    public Path(int coinType) {
        this.coinType = coinType;
    }

    public String getPath() {
        return M + "/" + PURPOSE + "/" + coinType + "H/" + this.account + "H/" + this.change + "/" + this.addressIndex;
    }


}
