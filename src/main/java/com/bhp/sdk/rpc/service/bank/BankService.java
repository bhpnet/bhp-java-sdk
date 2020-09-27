package com.bhp.sdk.rpc.service.bank;

import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.rpc.bean.Transfer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Trigger
 */
public interface BankService {


    /**
     * 根据交易信息构造交易
     * @param toAddress 接收地址
     * @param requestBody 交易信息
     * @return 广播交易对象
     * */
    @POST("/bank/accounts/{address}/transfers")
    Call<Transfer> getTransfers(@Path("address") String toAddress, @Body JSONObject requestBody);


}
