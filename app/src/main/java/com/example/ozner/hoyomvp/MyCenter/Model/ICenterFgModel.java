package com.example.ozner.hoyomvp.MyCenter.Model;

import android.content.Context;

import com.example.ozner.hoyomvp.Bean.AuthorityDetail;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;

import retrofit2.Call;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public interface ICenterFgModel {
    //获取当前权限的信息或者审核进度，获取团队成员信息
    Call<NetJsonResponse<AuthorityDetail>> getNowAuthOrityDetail(Context context, ResponseListener rl);
}
