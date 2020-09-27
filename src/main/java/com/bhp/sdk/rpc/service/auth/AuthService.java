package com.bhp.sdk.rpc.service.auth;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Trigger
 */
public interface AuthService {

    @GET("/auth/accounts/{address}")
    Call<JSONObject> getAddressInfo(@Path("address") String address);

}
