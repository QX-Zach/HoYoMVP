package com.example.ozner.hoyomvp.Bean;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public interface ResponseListener {
    void onSuccess(NetJsonResponse data);

    void onFailure(String errmsg);
}
