package com.example.ozner.hoyomvp.Model;

import android.content.Context;

import com.example.ozner.hoyomvp.Bean.IndexInfo;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;

import retrofit2.Call;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public interface InterfaceIndexModel {
    Call<NetJsonResponse<IndexInfo>> refreshIndexInfo(Context context, ResponseListener rl);
}
