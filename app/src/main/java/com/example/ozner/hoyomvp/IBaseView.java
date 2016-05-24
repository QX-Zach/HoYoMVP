package com.example.ozner.hoyomvp;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public interface IBaseView {
    void showLoading(String msg);

    void hiddeLoading();

    void showErrMsg(int state);

    void showToast(String msg);

    void finishAll();
}
