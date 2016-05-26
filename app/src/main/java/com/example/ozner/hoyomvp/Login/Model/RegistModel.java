package com.example.ozner.hoyomvp.Login.Model;

import com.example.ozner.hoyomvp.BaseModel;
import com.example.ozner.hoyomvp.HttpService.BaseCallBack;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.HoYoApplication;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class RegistModel extends BaseModel implements IRegistModel {

    @Override
    public void sendPhoneCode(String phone, final ResponseListener rl) {
        HoYoApplication.getHttpApiService().sendPhoneCode(phone, "register", "engineer").enqueue(new BaseCallBack<String>(rl));
    }

    //校验手机号和验证码
    @Override
    public void checkVerifyCode(String phone, String code, final ResponseListener rl) {
        HoYoApplication.getHttpApiService().checkVerifyCode(phone, code).enqueue(new BaseCallBack<String>(rl));
    }

    //注册提交
    @Override
    public void registSubmit(String token, String name, String cardid, String password, ResponseListener rl) {
        HoYoApplication.getHttpApiService().registSubmit(token, name, cardid, password, "engineer").enqueue(new BaseCallBack<String>(rl));
    }

}
