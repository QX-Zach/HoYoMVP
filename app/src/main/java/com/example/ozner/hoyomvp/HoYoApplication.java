package com.example.ozner.hoyomvp;

import android.app.Application;

import com.example.ozner.hoyomvp.HttpService.HttpApiService;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.Utils.LogUtilLC;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ozner_67 on 2016/5/23.
 */
public class HoYoApplication extends Application {
    public static final String BaseUrl = "http://wechat.hoyofuwu.com/";
    private static HttpApiService httpApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtilLC.init(getApplicationContext());
        initHttpApi();
    }

    private void initHttpApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpApiService = retrofit.create(HttpApiService.class);
    }

    public static HttpApiService getHttpApiService() {
        return httpApiService;
    }
}
