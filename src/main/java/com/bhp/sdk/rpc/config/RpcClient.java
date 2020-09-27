package com.bhp.sdk.rpc.config;

import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.exception.ApiException;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Trigger
 */

public class RpcClient {

    private RpcConfiguration config;
    private OkHttpClient client;
    private Retrofit retrofit;

    public RpcClient(RpcConfiguration config) {
        this.config = config;
        this.client = client();
        this.retrofit = new RpcRetrofit(config, this.client).retrofit();
    }

    public RpcClient(RpcConfiguration config,String url) {
        this.config = config;
        this.client = client();
        this.retrofit = new RpcRetrofit(config, this.client).retrofit(url);
    }

    private OkHttpClient client() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(this.config.getConnectTimeout(), TimeUnit.SECONDS);
        clientBuilder.readTimeout(this.config.getReadTimeOut(), TimeUnit.SECONDS);
        clientBuilder.writeTimeout(this.config.getWriteTimeOut(), TimeUnit.SECONDS);
        clientBuilder.retryOnConnectionFailure(true);
        return clientBuilder.build();
    }

    public <T> T createService(Class<T> service) throws IOException {
        return retrofit.create(service);
    }

    public <T> T execute(Call<T> call) throws IOException {
        Response<T> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new ApiException(response.code(), JSONObject.parseObject(response.errorBody().string()).getString("error"));
    }


}
