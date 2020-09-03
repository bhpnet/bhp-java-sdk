package com.bhp.sdk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.bean.Amount;
import com.bhp.sdk.bean.Fee;
import com.bhp.sdk.bean.Signer;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AccountTransferTest {

//    privateKey:d47fe557084d7a7a2fe1d4de90385e5cbc0a62b2c5ee1d12f474a0376c958447
//    sign:UI2fOBmFYVM53jo0+7zHFHLbhWyuXm4cYhvF4SIyvcNDIFGQk9ew2AvCSCPp4AYkIcUIxKpTV37UXb0bZad9lw==

    @Test
    public void signTest() {

        Amount amount = new Amount("abhp","5000000");

        Fee fee = new Fee("200000", Collections.singletonList(amount));

        Amount sendAmount = new Amount("abhp","10000000000");

        JSONArray jsonArray = new JSONArray().fluentAdd(sendAmount);

        JSONObject value = new JSONObject()
                .fluentPut("amount", jsonArray)
                .fluentPut("from_address", "bhp1arwh8ddg37fphh732yggmhkgk6gfjc97mckg0d")
                .fluentPut("to_address", "bhp1pw86qx0mlma5ajl46wjwgn34k3tjr2a7azg4ad");


        JSONObject msg = new JSONObject()
                .fluentPut("type", "cosmos-sdk/MsgSend")
                .fluentPut("value", value);

        try
        {
            String sign = Signer.createSigner()
                    .chainId("testing")
                    .memo("")
                    .sequence("400")
                    .accountNumber("16")
                    .fee(fee)
                    .msg(new JSONArray().fluentAdd(msg))
                    .privateKey("d47fe557084d7a7a2fe1d4de90385e5cbc0a62b2c5ee1d12f474a0376c958447")
                    .sign();

            System.out.println("签名后的字符串"+sign);

            assertEquals(sign, "UI2fOBmFYVM53jo0+7zHFHLbhWyuXm4cYhvF4SIyvcNDIFGQk9ew2AvCSCPp4AYkIcUIxKpTV37UXb0bZad9lw==");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

}
