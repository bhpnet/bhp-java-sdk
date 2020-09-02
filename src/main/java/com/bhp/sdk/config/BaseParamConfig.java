package com.bhp.sdk.config;

/**
 * @author Trigger
 */
public class BaseParamConfig {

    private String mainPrefix;
    private String passphrase;
    private String path;

    public String getMainPrefix() {
        return mainPrefix;
    }

    public void setMainPrefix(String mainPrefix) {
        this.mainPrefix = mainPrefix;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "BaseParamConfig{" +
                "mainPrefix='" + mainPrefix + '\'' +
                ", passphrase='" + passphrase + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
