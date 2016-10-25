package com.example.ozner.hoyomvp.Login.Presenter;

import android.content.Context;
import android.content.Intent;

import com.example.ozner.hoyomvp.Bean.IndexInfo;
import com.example.ozner.hoyomvp.IBaseView;
import com.example.ozner.hoyomvp.Utils.LogUtilLC;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.Login.LoginActivity;
import com.example.ozner.hoyomvp.Login.Model.ILoginModel;
import com.example.ozner.hoyomvp.Login.Model.LoginModel;
import com.example.ozner.hoyomvp.Login.views.IloginView;
import com.example.ozner.hoyomvp.Utils.HoYoPreference;

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

    public void reLogin(final Context context) {
        if (HoYoPreference.getUserToken(context)!=null&&!HoYoPreference.getUserToken(context).isEmpty()) {
            loginModel.reLogin(HoYoPreference.getUserToken(context), new ResponseListener<NetJsonResponse<IndexInfo>>() {
                @Override
                public void onSuccess(NetJsonResponse<IndexInfo> data) {
                    if (data != null) {
                        if (LogUtilLC.APP_DBG) {
                            LogUtilLC.E("tag", "登录：" + data.toString());
                        }
                        if (data.getState() > 0) {
                            loginView.loginSuccess();
                        } else if (data.getState() != 0 && data.getState() != -10015) {
                            baseView.showErrMsg(data.getState());
                        } else {
                            HoYoPreference.setUserToken(context, "");
                            baseView.finishAll();
                            Intent loginIntent = new Intent(context, LoginActivity.class);
                            context.startActivity(loginIntent);
                        }
                    }
                }

                @Override
                public void onFailure(String errmsg) {
                    baseView.showToast("请检查网络连接");
                }
            });
        }
    }

    public void login(final Context context) {
        if (loginView.getLoginMobile().length() == 11 && loginView.getLoginPassword().length() > 0) {
            baseView.showLoading("正在登录...");
            loginModel.Login(loginView.getLoginMobile(), loginView.getLoginPassword(), new ResponseListener<NetJsonResponse<String>>() {
                @Override
                public void onSuccess(NetJsonResponse<String> data) {
                    baseView.hiddeLoading();
                    if (data != null) {
                        if (LogUtilLC.APP_DBG) {
                            LogUtilLC.E("tag", "登录：" + data.toString());
                        }
                        if (data.getState() > 0) {
                            HoYoPreference.setUserID(context, data.getData());
                            HoYoPreference.setUserToken(context, data.getMsg());
                            loginView.loginSuccess();
                        } else if (data.getState() != 0 && data.getState() != -10015) {
                            baseView.showErrMsg(data.getState());
                        } else {
                            baseView.finishAll();
                            Intent loginIntent = new Intent(context, LoginActivity.class);
                            context.startActivity(loginIntent);
                        }
                    }
                }

                @Override
                public void onFailure(String errmsg) {
                    baseView.hiddeLoading();
                    if (LogUtilLC.APP_DBG) {
                        LogUtilLC.E("tag", "登录失败:" + errmsg);
                    }
                    baseView.showToast("请检查网络连接");
                }
            });
        } else {
            baseView.showToast("请输入正确的手机号和密码");
        }
    }

    public void cancleLogin() {
        loginModel.calcleLogin();
        baseView.hiddeLoading();
    }
}
