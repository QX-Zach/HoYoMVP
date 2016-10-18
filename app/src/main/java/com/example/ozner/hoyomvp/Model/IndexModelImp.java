package com.example.ozner.hoyomvp.Model;

import android.content.Context;

import com.example.ozner.hoyomvp.BaseModel;
import com.example.ozner.hoyomvp.Bean.IndexInfo;
import com.example.ozner.hoyomvp.HttpService.BaseCallBack;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.HoYoApplication;
import com.example.ozner.hoyomvp.Utils.HoYoPreference;

import retrofit2.Call;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public class IndexModelImp extends BaseModel implements InterfaceIndexModel {
    //刷新首页信息
    @Override
    public Call<NetJsonResponse<IndexInfo>> refreshIndexInfo(Context context, final ResponseListener rl) {
        Call<NetJsonResponse<IndexInfo>> call = HoYoApplication.getHttpApiService().refreshIndexInfo(HoYoPreference.getUserToken(context));
        call.enqueue(new BaseCallBack<IndexInfo>(rl));
        return call;
    }

}
