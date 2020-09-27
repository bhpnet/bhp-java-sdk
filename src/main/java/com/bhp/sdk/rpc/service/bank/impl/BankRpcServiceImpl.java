package com.bhp.sdk.rpc.service.bank.impl;

import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.rpc.bean.Sender;
import com.bhp.sdk.rpc.bean.Transfer;
import com.bhp.sdk.rpc.bean.TxInformation;
import com.bhp.sdk.rpc.config.RpcClient;
import com.bhp.sdk.rpc.service.bank.BankRpcService;
import com.bhp.sdk.rpc.service.bank.BankService;

import java.io.IOException;

/**
 * @author Trigger
 */
public class BankRpcServiceImpl implements BankRpcService {

    private RpcClient rpcClient;
    private BankService bankService;

    public BankRpcServiceImpl(RpcClient rpcClient) throws IOException {
        this.rpcClient = rpcClient;
        this.bankService = this.rpcClient.createService(BankService.class);
    }

    @Override
    public Transfer getTransfers(String toAddress, TxInformation txInformation) throws IOException {
        JSONObject requestBody = new JSONObject()
                .fluentPut("amount", txInformation.getAmounts())
                .fluentPut("base_req", txInformation);

        return this.rpcClient.execute(bankService.getTransfers(toAddress, requestBody));
    }
}
