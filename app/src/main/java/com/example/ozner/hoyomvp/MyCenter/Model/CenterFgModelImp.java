package com.example.ozner.hoyomvp.MyCenter.Model;

import android.content.Context;

import com.example.ozner.hoyomvp.BaseModel;
import com.example.ozner.hoyomvp.Bean.AuthorityDetail;
import com.example.ozner.hoyomvp.HttpService.BaseCallBack;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.HoYoApplication;
import com.example.ozner.hoyomvp.Utils.HoYoPreference;

import retrofit2.Call;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public class CenterFgModelImp extends BaseModel implements ICenterFgModel {

    @Override
    public Call<NetJsonResponse<AuthorityDetail>> getNowAuthOrityDetail(Context context, ResponseListener rl) {
        Call<NetJsonResponse<AuthorityDetail>> call = HoYoApplication.getHttpApiService().getNowAuthorityDetail(HoYoPreference.getUserToken(context));
        call.enqueue(new BaseCallBack(rl));
        return call;
    }
}
