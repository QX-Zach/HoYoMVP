package com.example.ozner.hoyomvp.Login.Presenter;

import com.example.ozner.hoyomvp.Bean.IBaseView;
import com.example.ozner.hoyomvp.Bean.LogUtilLC;
import com.example.ozner.hoyomvp.Bean.NetJsonResponse;
import com.example.ozner.hoyomvp.Bean.ResponseListener;
import com.example.ozner.hoyomvp.Login.Model.ILoginModel;
import com.example.ozner.hoyomvp.Login.Model.LoginModel;
import com.example.ozner.hoyomvp.Login.views.IloginView;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class LoginPresenter {
    private ILoginModel loginModel;
    private IloginView loginView;
    private IBaseView baseView;

    public LoginPresenter(IBaseView baseView, IloginView loginView) {
        this.baseView = baseView;
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login() {
        baseView.showLoading("正在登录...");
        loginModel.Login(loginView.getLoginMobile(), loginView.getLoginPassword(), new ResponseListener() {
            @Override
            public void onSuccess(NetJsonResponse data) {
                baseView.hiddeLoading();
                if (data != null) {
                    if (LogUtilLC.APP_DBG) {
                        LogUtilLC.E("tag", "state:" + data.getState() + ",usertoken:" + data.getMsg() + ",userid:" + data.getData());
                    }
                    loginView.loginSuccess();
                }

            }

            @Override
            public void onFailure(String errmsg) {
                baseView.hiddeLoading();
                if (LogUtilLC.APP_DBG) {
                    LogUtilLC.E("tag", "登录失败:" + errmsg);
                }
                loginView.showFailedErr(errmsg);
            }
        });
    }

    public void cancleLogin() {
        loginModel.cancleLogin();
        baseView.hiddeLoading();
    }
}
