package com.example.ozner.hoyomvp.Login.Model;

import com.example.ozner.hoyomvp.BaseModel;
import com.example.ozner.hoyomvp.Bean.IndexInfo;
import com.example.ozner.hoyomvp.HoYoApplication;
import com.example.ozner.hoyomvp.HttpService.BaseCallBack;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class LoginModel extends BaseModel implements ILoginModel {

    @Override
    public void Login(String phone, String passwor, final ResponseListener rl) {
        HoYoApplication.getHttpApiService().login(phone, passwor).enqueue(new BaseCallBack<String>(rl));
    }

    @Override
    public void reLogin(String usertoken,final ResponseListener rl) {
        HoYoApplication.getHttpApiService().refreshIndexInfo(usertoken).enqueue(new BaseCallBack<IndexInfo>(rl));
    }

    @Override
    public void calcleLogin() {
        cancleHttp();
    }
}
