package com.example.ozner.hoyomvp.MyCenter.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.ozner.hoyomvp.Bean.AuthorityDetail;
import com.example.ozner.hoyomvp.Bean.IndexInfo;
import com.example.ozner.hoyomvp.Utils.LogUtilLC;
import com.example.ozner.hoyomvp.HttpService.NetJsonResponse;
import com.example.ozner.hoyomvp.HttpService.ResponseListener;
import com.example.ozner.hoyomvp.Login.LoginActivity;
import com.example.ozner.hoyomvp.Model.IndexModelImp;
import com.example.ozner.hoyomvp.Model.InterfaceIndexModel;
import com.example.ozner.hoyomvp.MyCenter.Model.CenterFgModelImp;
import com.example.ozner.hoyomvp.MyCenter.Model.ICenterFgModel;
import com.example.ozner.hoyomvp.MyCenter.views.ICenterFgView;
import com.example.ozner.hoyomvp.Utils.NetErrDecode;
import com.example.ozner.hoyomvp.Utils.UserDataPreference;

import retrofit2.Call;

/**
 * Created by ozner_67 on 2016/5/25.
 */
public class CenterFgPresenter {
    InterfaceIndexModel indexModel;
    ICenterFgModel centerFgModel;
    ICenterFgView centerFgView;
    Call<NetJsonResponse<IndexInfo>> refreshCall;
    Call<NetJsonResponse<AuthorityDetail>> authCall;
    boolean isGoTeam = false;

    public CenterFgPresenter(ICenterFgView centerFgView) {
        this.centerFgView = centerFgView;
        indexModel = new IndexModelImp();
        centerFgModel = new CenterFgModelImp();
    }

    public void initData(Context context) {
        initFromLocal(context);
        refreshIndexInfo(context);
        getNowAuthOrityDetail(context);
    }

    private void initFromLocal(Context context) {
        String indexInfoString = UserDataPreference.GetUserData(context, UserDataPreference.IndexInfo, "");
        if (LogUtilLC.APP_DBG) {
            LogUtilLC.E("tag", "Local_indexInfo:" + indexInfoString);
        }
        if (indexInfoString != "") {
            IndexInfo indexInfo = JSON.parseObject(indexInfoString, IndexInfo.class);
            centerFgView.showUserInfo(indexInfo);
        }
    }

    //刷新首页信息
    private void refreshIndexInfo(final Context context) {
        refreshCall = indexModel.refreshIndexInfo(context, new ResponseListener<NetJsonResponse<IndexInfo>>() {
            @Override
            public void onSuccess(NetJsonResponse<IndexInfo> data) {
                if (data != null) {
                    if (LogUtilLC.APP_DBG) {
                        LogUtilLC.E("tag", "刷新首页信息：" + data.getState() + " , data:" + JSON.toJSONString(data.getData()));
                    }
                    if (data.getState() > 0) {
                        UserDataPreference.SetUserData(context, UserDataPreference.IndexInfo, JSON.toJSONString(data.getData()));
                        centerFgView.showUserInfo(data.getData());
                    } else if (data.getState() == -10015) {
                        centerFgView.finishActivity();
                    } else if (data.getState() != 0) {
                        NetErrDecode.ShowErrMsgDialog(context, data.getState(), "未知错误");
                    }
                } else {
                    NetErrDecode.ShowErrMsgDialog(context, -1, "未知错误");
//                    Toast.makeText(context,"refreshIndexInfo 是空" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errmsg) {
//                NetErrDecode.ShowErrMsgDialog(context, -1, "未知错误");
                Toast.makeText(context, errmsg, Toast.LENGTH_SHORT).show();
                if (LogUtilLC.APP_DBG) {
                    LogUtilLC.E("tag", "刷新首页信息_Ex：" + errmsg);
                }
            }
        });
    }

    //获取我的团队信息
    private void getNowAuthOrityDetail(final Context context) {
        authCall = centerFgModel.getNowAuthOrityDetail(context, new ResponseListener<NetJsonResponse<AuthorityDetail>>() {
            @Override
            public void onSuccess(NetJsonResponse<AuthorityDetail> data) {
                if (data != null) {
                    if (LogUtilLC.APP_DBG) {
                        LogUtilLC.E("tag", "CenterAuth:" + +data.getState() + " , data:" + JSON.toJSONString(data.getData()));
                    }
                    if (data.getState() > 0) {
                        //加入团队或创建团队成功
                        isGoTeam = true;
                        UserDataPreference.SetUserData(context, UserDataPreference.AuthorityDetail, JSON.toJSONString(data.getData()));
                    } else if (data.getState() == 0) {
                        UserDataPreference.SetUserData(context, UserDataPreference.AuthorityDetail, "");
                    } else if (data.getState() == -10015) {
                        context.startActivity(new Intent(context, LoginActivity.class));
                        centerFgView.finishActivity();
                    }
                }
            }

            @Override
            public void onFailure(String errmsg) {
                if (LogUtilLC.APP_DBG) {
                    LogUtilLC.E("tag", "获取团队信息_Ex：" + errmsg);
                }
            }
        });
    }

    //取消网络请求
    public void cancleHttp() {
        if (authCall != null && !authCall.isCanceled()) {
            authCall.cancel();
        }
        if (refreshCall != null && !refreshCall.isCanceled()) {
            refreshCall.cancel();
        }
    }
}
