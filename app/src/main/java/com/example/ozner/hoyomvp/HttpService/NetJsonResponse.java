package com.example.ozner.hoyomvp.HttpService;

import org.json.JSONObject;

import java.io.PipedReader;
import java.util.concurrent.ExecutionException;

/**
 * Created by ozner_67 on 2016/5/23.
 */
public class NetJsonResponse<T> {
    private int state;
    private String msg;
    private T data;

    @Override
    public String toString() {
        return "NetJsonResponse{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
