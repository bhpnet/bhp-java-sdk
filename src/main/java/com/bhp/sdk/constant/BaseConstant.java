package com.bhp.sdk.constant;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

/**
 * @author Trigger
 */
public class BaseConstant {

    public static final int DEFAULT_COIN_TYPE = 547;
    public static final String DEFAULT_BHP_PREFIX = "bhp";
    public static final String DEFAULT_PASSPHRASE = "";
    public static final String DEFAULT_DEANOM = "abhp";
    public static final String DEFAULT_AMOUNT = "2.5";

    /**
     * 助记词长度
     */
    public enum WordLength {
        /**
         * 助记词长度常量
         */
        TWELVE(16),
        TWENTY_FOUR(32);
        private int length;

        WordLength(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    public enum RpcConstant {
        /**
         * RPC常量
         * */
        READ_TIMEOUT(30),
        WRITE_TIMEOUT(30),
        CONNECT_TIMEOUT(30),
        ;
        private int value;

        RpcConstant(int value) {
        }

        public int getValue() {
            return value;
        }
    }


}
