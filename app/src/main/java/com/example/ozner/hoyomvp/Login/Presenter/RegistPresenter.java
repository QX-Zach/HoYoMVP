package com.example.ozner.hoyomvp.Login.Presenter;

import android.content.Context;

import com.example.ozner.hoyomvp.Utils.LogUtilLC;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.IBaseView;
import com.example.ozner.hoyomvp.Login.Model.IRegistModel;
import com.example.ozner.hoyomvp.Login.Model.RegistModel;
import com.example.ozner.hoyomvp.Login.views.IRegistView;
import com.example.ozner.hoyomvp.Utils.HoYoPreference;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class RegistPresenter {
    IRegistModel registModel;
    IRegistView registView;
    IBaseView baseView;
    private String verifyToken = "";

    public RegistPresenter(IBaseView baseView, IRegistView registView) {
        this.registView = registView;
        this.baseView = baseView;
        this.registModel = new RegistModel();
    }

    //发送验证码
    public void sendPhoneCode() {
        if (registView.getRegistMobile() != null && registView.getRegistMobile().length() > 0) {
            baseView.showLoading("正在发送验证码...");
            registModel.sendPhoneCode(registView.getRegistMobile(), new ResponseListener<NetJsonResponse<String>>() {
                @Override
                public void onSuccess(NetJsonResponse<String> data) {
                    baseView.hiddeLoading();
                    if (data != null) {
                        if (data.getState() > 0) {
                            if (LogUtilLC.APP_DBG) {
                                LogUtilLC.E("tag", "发送验证码:" + data.toString());
                            }
                            registView.goRegStepTwo();
                        } else if (data.getState() != 0 && data.getState() != -10015) {
                            baseView.showErrMsg(data.getState());
                        }
                    }
                }

                @Override
                public void onFailure(String errmsg) {
                    baseView.hiddeLoading();
                    baseView.showToast("发送验证码失败，请检查网络并重试");
                }
            });
        } else {
            baseView.showToast("请输入正确的手机号");
        }
    }

    //校验手机号和验证码
    public void checkVerifyCode() {
        if (registView.getVerifyCode().length() > 0) {
            baseView.showLoading("正在验证，请稍后...");
            registModel.checkVerifyCode(registView.getRegistMobile(), registView.getVerifyCode(), new ResponseListener<NetJsonResponse<String>>() {
                @Override
                public void onSuccess(NetJsonResponse<String> data) {
                    baseView.hiddeLoading();
                    if (data != null) {
                        if (data.getState() > 0) {
                            verifyToken = data.getMsg();
                            registView.goRegStepThree();
                        } else if (data.getState() != 0 && data.getState() != -10015) {
                            baseView.showErrMsg(data.getState());
                        }
                    }
                }

                @Override
                public void onFailure(String errmsg) {
                    baseView.hiddeLoading();
                    baseView.showToast("网络请求失败，请检查网络并重试");
                }
            });
        } else {
            baseView.showToast("请输入验证码");
        }
    }

    public void registSubmit(final Context context) {
        boolean hasVerify = true;
        String realname = registView.getRegisterName();
        String cardid = registView.getRegisterIDNo();
        String password = registView.getRegisterPassword();
        if (realname == null || realname.length() == 0) {
            hasVerify = false;
            baseView.showToast("请填写姓名");

        } else if (cardid == null || cardid == "" || cardid.length() != 18) {
            hasVerify = false;
            baseView.showToast("请填写18位身份证号");
        } else if (password == null || password.length() < 6) {
            hasVerify = false;
            baseView.showToast("请填写6位以上的密码");
        }
        if (hasVerify) {
            baseView.showLoading("正在提交...");
            registModel.registSubmit(verifyToken, realname, cardid, password, new ResponseListener<NetJsonResponse<String>>() {
                @Override
                public void onSuccess(NetJsonResponse<String> data) {
                    baseView.hiddeLoading();
                    if (data != null) {
                        if (data.getState() > 0) {
                            HoYoPreference.setUserToken(context, data.getMsg());
                            HoYoPreference.setUserID(context, data.getData());
                            registView.registSuccess();
                        } else if (data.getState() == -10015) {
                            baseView.finishAll();
                        } else if (data.getState() != 0) {
                            baseView.showErrMsg(data.getState());
                        }
                    }
                }

                @Override
                public void onFailure(String errmsg) {
                    baseView.hiddeLoading();
                    baseView.showToast("网络请求失败，请检查网络并重试");
                }
            });
        }
    }

}
