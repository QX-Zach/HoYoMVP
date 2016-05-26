package com.example.ozner.hoyomvp.HttpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class BaseCallBack<T> implements Callback<NetJsonResponse<T>> {
    private ResponseListener rl;

    public BaseCallBack(final ResponseListener rl) {
        this.rl = rl;
    }

    @Override
    public void onResponse(Call<NetJsonResponse<T>> call, Response<NetJsonResponse<T>> response) {
        if (rl != null) {
            rl.onSuccess(response.body());
        }
    }

    @Override
    public void onFailure(Call<NetJsonResponse<T>> call, Throwable t) {
        if (rl != null) {
            rl.onFailure(t.getMessage());
        }
    }
}
