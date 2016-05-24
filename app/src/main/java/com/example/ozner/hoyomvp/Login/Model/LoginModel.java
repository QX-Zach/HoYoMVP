package com.example.ozner.hoyomvp.Login.Model;

import com.example.ozner.hoyomvp.Bean.HttpApiService;
import com.example.ozner.hoyomvp.Bean.NetJsonResponse;
import com.example.ozner.hoyomvp.Bean.ResponseListener;
import com.example.ozner.hoyomvp.HoYoApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class LoginModel implements ILoginModel {
    private Call<NetJsonResponse> loginCall;

    @Override
    public void Login(String phone, String passwor, final ResponseListener rl) {
        loginCall = HoYoApplication.getHttpApiService().login(phone, passwor);
        loginCall.enqueue(new Callback<NetJsonResponse>() {
            @Override
            public void onResponse(Call<NetJsonResponse> call, Response<NetJsonResponse> response) {
                rl.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NetJsonResponse> call, Throwable t) {
                rl.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void cancleLogin() {
        if (loginCall != null) {
            loginCall.cancel();
        }
    }
}
