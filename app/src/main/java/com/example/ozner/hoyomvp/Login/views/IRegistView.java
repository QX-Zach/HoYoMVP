package com.example.ozner.hoyomvp.Login.views;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public interface IRegistView {
    String getRegistMobile();

    String getVerifyCode();

    void goRegStepTwo();

    void goRegStepThree();

    String getRegisterName();

    String getRegisterIDNo();

    String getRegisterPassword();

    String getRegisterInviteCode();
    void registSuccess();
}
