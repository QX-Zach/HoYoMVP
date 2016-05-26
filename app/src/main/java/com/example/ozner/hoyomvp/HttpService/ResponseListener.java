package com.example.ozner.hoyomvp.HttpService;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public interface ResponseListener<T> {
    void onSuccess(T data);

    void onFailure(String errmsg);
}
