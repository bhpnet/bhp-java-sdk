package com.bhp.sdk.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HexUtils {

    public static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
            //1得到一位的进行补0操作
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static String decode(String encode) throws NoSuchAlgorithmException, IOException {
        if (StringUtils.isNotBlank(encode)) {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] bytes = base64Decoder.decodeBuffer(encode);
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(bytes);
            byte[] digest = instance.digest();
            String decode = HexUtils.byte2Hex(digest).toUpperCase();
            return decode.toUpperCase();
        }
        throw new RuntimeException("encode must not be empty");
    }
}
