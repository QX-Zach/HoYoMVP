package com.example.ozner.hoyomvp.Bean;

import android.net.nsd.NsdManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class BaseCallBack implements Callback<NetJsonResponse> {
    private ResponseListener rl;

    public BaseCallBack(final ResponseListener rl) {
        this.rl = rl;
    }

    @Override
    public void onResponse(Call<NetJsonResponse> call, Response<NetJsonResponse> response) {
        if (rl != null) {
            rl.onSuccess(response.body());
        }
    }

    @Override
    public void onFailure(Call<NetJsonResponse> call, Throwable t) {
        if (rl != null) {
            rl.onFailure(t.getMessage());
        }
    }
}
