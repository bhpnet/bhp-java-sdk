package com.bhp.sdk.rpc.service.tx;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TxService {


    /**
     * 根据hash获取交易详情
     * @param hash tx_hash
     * @return 交易详情
     * */
    @GET("/txs/{hash}")
    Call<JSONObject> getTxByHash(@Path("hash") String hash);

    /**
     * 广播交易
     * @param broadcastTxReq 交易请求体
     * @return 返回数据
     * */
    @POST("/txs")
    Call<JSONObject> broadcastTx(@Body JSONObject broadcastTxReq);




}
