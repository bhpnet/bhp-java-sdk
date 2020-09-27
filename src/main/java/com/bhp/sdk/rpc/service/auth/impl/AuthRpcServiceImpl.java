package com.bhp.sdk.rpc.service.auth.impl;

import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.rpc.bean.AccountInfo;
import com.bhp.sdk.rpc.config.RpcClient;
import com.bhp.sdk.rpc.service.auth.AuthRpcService;
import com.bhp.sdk.rpc.service.auth.AuthService;

import java.io.IOException;

/**
 * @author Trigger
 */
public class AuthRpcServiceImpl implements AuthRpcService {

    private RpcClient rpcClient;
    private AuthService authService;

    public AuthRpcServiceImpl(RpcClient rpcClient) throws IOException {
        this.rpcClient = rpcClient;
        this.authService = rpcClient.createService(AuthService.class);
    }

    @Override
    public AccountInfo getAddressInfo(String address) throws IOException {
        JSONObject response = this.rpcClient.execute(this.authService.getAddressInfo(address));
        AccountInfo account = response.getObject("result", AccountInfo.class);
        account.setHeight(response.getLong("height"));
        return account;

    }
}
