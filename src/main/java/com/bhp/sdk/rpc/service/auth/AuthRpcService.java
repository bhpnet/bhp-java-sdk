package com.bhp.sdk.rpc.service.auth;

import com.bhp.sdk.rpc.bean.AccountInfo;

import java.io.IOException;

/**
 * @author Trigger
 */
public interface AuthRpcService {


    AccountInfo getAddressInfo(String address) throws IOException;



}
