package com.example.ozner.hoyomvp.Login.Model;

import com.example.ozner.hoyomvp.BaseModel;
import com.example.ozner.hoyomvp.HttpService.BaseCallBack;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.HoYoApplication;
import com.example.ozner.hoyomvp.Utils.LogUtilLC;
import com.google.gson.internal.Excluder;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class LoginModel extends BaseModel implements ILoginModel {

    @Override
    public void Login(String phone, String passwor, final ResponseListener rl) {
        HoYoApplication.getHttpApiService().login(phone, passwor).enqueue(new BaseCallBack<String>(rl));
    }

    @Override
    public void calcleLogin() {
        cancleHttp();
    }
}
