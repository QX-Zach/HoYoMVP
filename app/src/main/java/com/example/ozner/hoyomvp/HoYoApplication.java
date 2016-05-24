package com.example.ozner.hoyomvp;

import android.app.Application;

import com.example.ozner.hoyomvp.Bean.HttpApiService;
import com.example.ozner.hoyomvp.Bean.LogUtilLC;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ozner_67 on 2016/5/23.
 */
public class HoYoApplication extends Application {
    private static HttpApiService httpApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtilLC.init(getApplicationContext());
        initHttpApi();
    }

    private void initHttpApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wechat.hoyofuwu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpApiService = retrofit.create(HttpApiService.class);
    }

    public static HttpApiService getHttpApiService() {
        return httpApiService;
    }
}
