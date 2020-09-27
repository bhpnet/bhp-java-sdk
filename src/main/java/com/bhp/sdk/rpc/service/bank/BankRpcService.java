package com.bhp.sdk.rpc.service.bank;

import com.bhp.sdk.rpc.bean.Sender;
import com.bhp.sdk.rpc.bean.Transfer;
import com.bhp.sdk.rpc.bean.TxInformation;

import java.io.IOException;

/**
 * @author Trigger
 */
public interface BankRpcService {


    Transfer getTransfers(String toAddress, TxInformation txInformation) throws IOException;

}
