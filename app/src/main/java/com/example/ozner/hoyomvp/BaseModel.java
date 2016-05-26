package com.example.ozner.hoyomvp;

import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;

import retrofit2.Call;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class BaseModel {
    protected Call<NetJsonResponse> httpCall;

    public void cancleHttp() {
        if (httpCall != null && !httpCall.isCanceled()) {
            httpCall.cancel();
            httpCall = null;
        }
    }
}
