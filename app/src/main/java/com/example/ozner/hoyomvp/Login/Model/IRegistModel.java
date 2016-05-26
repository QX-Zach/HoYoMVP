package com.example.ozner.hoyomvp.Login.Model;

import com.example.ozner.hoyomvp.HttpService.ResponseListener;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public interface IRegistModel {
    //发送手机验证码
    void sendPhoneCode(String phone, ResponseListener rl);

    //校验手机验证码
    void checkVerifyCode(String phone, String code, ResponseListener rl);

    //注册提交
    void registSubmit(String token, String name, String cardid, String password, ResponseListener rl);
}
