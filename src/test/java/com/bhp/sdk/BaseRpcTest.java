package com.bhp.sdk;

import com.bhp.sdk.bean.BhpAccount;
import com.bhp.sdk.component.Account;
import com.bhp.sdk.rpc.bean.AccountInfo;
import com.bhp.sdk.rpc.bean.AddressInfo;
import com.bhp.sdk.rpc.bean.Signature;
import com.bhp.sdk.rpc.bean.Transfer;
import com.bhp.sdk.rpc.config.RpcClient;
import com.bhp.sdk.rpc.config.RpcConfiguration;
import com.bhp.sdk.rpc.service.auth.AuthRpcService;
import com.bhp.sdk.rpc.service.auth.impl.AuthRpcServiceImpl;
import com.bhp.sdk.rpc.service.bank.BankRpcService;
import com.bhp.sdk.rpc.service.bank.impl.BankRpcServiceImpl;
import com.bhp.sdk.rpc.service.tx.TxRpcService;
import com.bhp.sdk.rpc.service.tx.impl.TxRpcServiceImpl;

import java.io.IOException;

public class BaseRpcTest {

    static RpcClient rpcClient;
    static final String mnemonic = "poem budget security victory visual settle steel fruit special rescue one narrow ladder bleak fan manage grocery rural pull bulk debate rocket one puppy";
    static Account account;
    Transfer transfer;
    AccountInfo accountInfo;
    String sign;
    Signature signature;

    static {
        RpcConfiguration rpcConfiguration = new RpcConfiguration();
        rpcConfiguration.setUnderScoreToCamelCase(true);
        rpcConfiguration.setUrl("http://47.103.38.41:26690");
        rpcClient = new RpcClient(rpcConfiguration);
        account = BhpAccount.createAccountFromMnemonic(mnemonic).build();
    }


    public TxRpcService getTxRpcService() throws IOException {
        return new TxRpcServiceImpl(rpcClient);
    }

    public AuthRpcService getAuthRpcService() throws IOException {
        return new AuthRpcServiceImpl(rpcClient);
    }

    public BankRpcService getBankRpcService() throws IOException {
        return new BankRpcServiceImpl(rpcClient);
    }

}
