package com.example.ozner.hoyomvp.Login.Model;

import com.example.ozner.hoyomvp.BaseModel;
import com.example.ozner.hoyomvp.Bean.BaseCallBack;
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
public class LoginModel extends BaseModel implements ILoginModel {

    @Override
    public void Login(String phone, String passwor, final ResponseListener rl) {
        httpCall = HoYoApplication.getHttpApiService().login(phone, passwor);
        httpCall.enqueue(new BaseCallBack(rl));
    }

    @Override
    public void calcleLogin() {
        cancleHttp();
    }
}
