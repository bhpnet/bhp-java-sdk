package com.bhp.sdk.rpc.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Trigger
 */
public class RpcRetrofit {

    private RpcConfiguration rpcConfiguration;
    private OkHttpClient client;

    public RpcRetrofit(RpcConfiguration rpcConfiguration, OkHttpClient client) {
        this.rpcConfiguration = rpcConfiguration;
        this.client = client;
    }

    public Retrofit retrofit() {
        return this.retrofit(rpcConfiguration.getUrl());
    }

    public Retrofit retrofit(String url) {
        Retrofit.Builder builder = new Retrofit.Builder();
        GsonConverterFactory factory;
        if (this.rpcConfiguration.isUnderScoreToCamelCase()) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
            factory = GsonConverterFactory.create(gsonBuilder.create());
        } else {
            factory = GsonConverterFactory.create();
        }
        builder.addConverterFactory(factory);
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(this.client);
        builder.baseUrl(url);
        return builder.build();
    }

}
