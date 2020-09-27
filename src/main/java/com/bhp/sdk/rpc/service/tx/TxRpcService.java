package com.bhp.sdk.rpc.service.tx;

import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.rpc.bean.BroadcastTx;

import java.io.IOException;

/**
 * @author Trigger
 */
public interface TxRpcService {

    JSONObject getTxByHash(String hash) throws IOException;

    JSONObject txBroadcast(BroadcastTx tx, String mode) throws IOException;

}
