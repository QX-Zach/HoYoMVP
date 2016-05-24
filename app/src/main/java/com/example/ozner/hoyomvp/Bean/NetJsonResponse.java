package com.example.ozner.hoyomvp.Bean;

import java.io.PipedReader;

/**
 * Created by ozner_67 on 2016/5/23.
 */
public class NetJsonResponse {
    private int state;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "state:" + state + ",msg:" + msg + ",data:" + data;
    }
}
