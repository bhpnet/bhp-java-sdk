package com.bhp.sdk;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.bean.BhpAccount;
import com.bhp.sdk.bean.Signer;
import com.bhp.sdk.rpc.bean.*;
import com.bhp.sdk.rpc.service.auth.AuthRpcService;
import com.bhp.sdk.rpc.service.bank.BankRpcService;
import com.bhp.sdk.rpc.service.tx.TxRpcService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import static org.junit.Assert.*;

public class RpcTest extends BaseRpcTest {


    @Test
    public void authRpcService_getAddressInfo() throws IOException {
        AuthRpcService authRpcService = getAuthRpcService();
        AccountInfo accountInfo = authRpcService.getAddressInfo(account.getAddress());
        assertNotNull(accountInfo.getHeight());
        assertNotNull(accountInfo.getType());
        assertNotNull(accountInfo.getValue());
        assertNotNull(accountInfo.getValue().getAccountNumber());
        assertNotNull(accountInfo.getValue().getSequence());
        this.accountInfo = accountInfo;
    }

    @Test
    public void txRpcService_getTxByHash() throws IOException {
        TxRpcService txRpcService = getTxRpcService();
        JSONObject tx = txRpcService.getTxByHash("9D841CFE863440BF31EF805B6B297CD01B285031980AF0730F9670947018AC23");
        assertNotNull(tx.get("txhash"));
        assertNotNull(tx.get("height"));
        assertNotNull(tx.get("tx"));
    }

    @Test
    public void bankRpcService_getTransfers_AutoGas() throws IOException {
        authRpcService_getAddressInfo();
        TxInformation txInformation = TxInformation
                .createTxInfoAutoCalculationGas()
                .accountNumber(accountInfo.getValue().getAccountNumber())
                .amount(new Amount().setAmount("100000").setDenom("abhp"))
                .chainId("testing")
                .from(account.getAddress())
                .memo("")
                .sequence(accountInfo.getValue().getSequence())
                .simulate(false)
                .build();

        BankRpcService bankRpcService = getBankRpcService();
        Transfer transfer = bankRpcService.getTransfers("bhp16lpvc5z38r858w75xyatclntmzrrhzth07wq69", txInformation);
        assertNotNull(transfer);
        this.transfer = transfer;
        System.out.println(JSON.toJSONString(transfer));
    }

    @Test
    public void bankRpcService_getTransfers_CustomGas() throws IOException {
        authRpcService_getAddressInfo();
        TxInformation txInformation = TxInformation
                .createTxInformation()
                .accountNumber(accountInfo.getValue().getAccountNumber())
                .amount(new Amount("abhp", "1000000"))
                .chainId("testing")
                .from(account.getAddress())
                .memo("")
                .sequence(accountInfo.getValue().getSequence())
                .simulate(false)
                .fee(new Amount("abhp", "1000000"))
                .build();
        BankRpcService bankRpcService = getBankRpcService();
        Transfer transfer = bankRpcService.getTransfers("bhp16lpvc5z38r858w75xyatclntmzrrhzth07wq69", txInformation);
        assertNotNull(transfer);
        this.transfer = transfer;
        System.out.println(JSON.toJSONString(transfer));
    }

    @Test
    public void signature() throws NoSuchAlgorithmException, IOException {
        Signer signer = Signer.createSigner()
                .chainId("testing")
                .memo(transfer.getValue().getMemo())
                .sequence(accountInfo.getValue().getSequence())
                .accountNumber(accountInfo.getValue().getAccountNumber())
                .fee(transfer.getValue().getFee())
                .msg(transfer.getValue().getMsg())
                .privateKey(account.getPrivateKey())
                .sign();
//        assertEquals(signer.value(), "qJnzAD2bQyWRMuoizTtpLhGSPAp6xaWUeAqR8+UXIaVwK5qiZD5D/csP8fHtLe1yzf3V0ATi3rS7lAm4EDpLtw==");
        signature = signer.signature(account);
//        assertEquals(signature.getSignature(), "qJnzAD2bQyWRMuoizTtpLhGSPAp6xaWUeAqR8+UXIaVwK5qiZD5D/csP8fHtLe1yzf3V0ATi3rS7lAm4EDpLtw==");
        System.out.println(JSON.toJSONString(signature));
    }

    public void autoGas_signature() throws IOException, NoSuchAlgorithmException {
        bankRpcService_getTransfers_AutoGas();
        signature();
    }

    public void customGas_signature() throws IOException, NoSuchAlgorithmException {
        bankRpcService_getTransfers_CustomGas();
        signature();
    }

    @Test
    public void txRpcService_broadCastTx_auto() throws IOException, NoSuchAlgorithmException {
        autoGas_signature();
        BroadcastTx broadcastTx = new BroadcastTx(transfer,Collections.singletonList(signature));
        JSONObject jsonObject = getTxRpcService().txBroadcast(broadcastTx, "sync");
        assertNull(jsonObject.get("code"));
        System.out.println(jsonObject);
    }
    @Test
    public void txRpcService_broadCastTx_custom() throws IOException, NoSuchAlgorithmException {
        customGas_signature();
        BroadcastTx broadcastTx = new BroadcastTx(transfer,Collections.singletonList(signature));
        JSONObject jsonObject = getTxRpcService().txBroadcast(broadcastTx, "sync");
        assertNull(jsonObject.get("code"));
        System.out.println(jsonObject);
    }


}
