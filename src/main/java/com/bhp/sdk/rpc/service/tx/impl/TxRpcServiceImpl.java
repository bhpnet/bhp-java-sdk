package com.bhp.sdk.rpc.service.tx.impl;

import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.rpc.bean.BroadcastTx;
import com.bhp.sdk.rpc.config.RpcClient;
import com.bhp.sdk.rpc.service.tx.TxRpcService;
import com.bhp.sdk.rpc.service.tx.TxService;

import java.io.IOException;

/**
 * @author Trigger
 */
public class TxRpcServiceImpl implements TxRpcService {

    private RpcClient rpcClient;
    private TxService txService;

    public TxRpcServiceImpl(RpcClient rpcClient) throws IOException {
        this.rpcClient = rpcClient;
        this.txService = rpcClient.createService(TxService.class);
    }

    @Override
    public JSONObject getTxByHash(String hash) throws IOException {
            return this.rpcClient.execute(txService.getTxByHash(hash));
    }

    @Override
    public JSONObject txBroadcast(BroadcastTx tx, String mode) throws IOException {
        JSONObject request = new JSONObject();
        request.fluentPut("tx", tx).fluentPut("mode", mode);
        return this.rpcClient.execute(txService.broadcastTx(request));
    }
}
