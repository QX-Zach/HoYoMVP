package com.example.ozner.hoyomvp.MyCenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ozner.hoyomvp.BaseFragment;
import com.example.ozner.hoyomvp.Bean.IndexInfo;
import com.example.ozner.hoyomvp.HoYoApplication;
import com.example.ozner.hoyomvp.MyCenter.Presenter.CenterFgPresenter;
import com.example.ozner.hoyomvp.MyCenter.views.ICenterFgView;
import com.example.ozner.hoyomvp.R;
import com.example.ozner.hoyomvp.Utils.HoYoPreference;
import com.example.ozner.hoyomvp.Utils.ImageHelper;
import com.example.ozner.hoyomvp.Utils.LogUtilLC;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends BaseFragment implements ICenterFgView {
    @InjectView(R.id.iv_center_headImg)
    ImageView ivCenterHeadImg;
    @InjectView(R.id.tv_center_name)
    TextView tvCenterName;
    @InjectView(R.id.tv_center_userid)
    TextView tvCenterUserid;
    @InjectView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @InjectView(R.id.rlay_personalInfo)
    RelativeLayout rlayPersonalInfo;
    @InjectView(R.id.rlay_auth_btn)
    RelativeLayout rlayAuthBtn;
    @InjectView(R.id.rlay_my_exam)
    RelativeLayout rlayMyExam;
    @InjectView(R.id.rlay_quit_parter)
    RelativeLayout rlayQuitParter;
    @InjectView(R.id.rlay_star)
    RelativeLayout rlayStar;
    @InjectView(R.id.rlay_service)
    RelativeLayout rlayService;
    @InjectView(R.id.rlay_site)
    RelativeLayout rlaySite;
    @InjectView(R.id.rlay_setup)
    RelativeLayout rlaySetup;
    ImageHelper imageHelper;
    CenterFgPresenter centerFgPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, null, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        centerFgPresenter = new CenterFgPresenter(this);
        imageHelper = new ImageHelper(getContext());
        centerFgPresenter.initData(getContext());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void finishActivity() {
        fagmentListener.finishAcitity();
    }

    @Override
    public void showUserInfo(IndexInfo indexInfo) {
        if (indexInfo.getUser().getHeadimageurl() != null && indexInfo.getUser().getHeadimageurl() != "") {
            if (indexInfo.getUser().getHeadimageurl().startsWith("http:")) {
                imageHelper.loadImage(ivCenterHeadImg, indexInfo.getUser().getHeadimageurl(), ImageHelper.LoadImageType.Round);
            } else {
                imageHelper.loadImage(ivCenterHeadImg, HoYoApplication.BaseUrl + indexInfo.getUser().getHeadimageurl(), ImageHelper.LoadImageType.Round);
            }
        }

        if (indexInfo.getUser().getNickname() != null && indexInfo.getUser().getNickname() != "") {
            tvUserPhone.setText(indexInfo.getUser().getMobile());
        }
        tvCenterUserid.setText("(工号:" + String.valueOf(indexInfo.getUser().getUserid()) + ")");

        if (indexInfo.getRealname() != null) {
            tvCenterName.setText(indexInfo.getRealname().getName());
        } else {
            if (indexInfo.getUser().getNickname() != null && indexInfo.getUser().getNickname() != "") {
                tvCenterName.setText(indexInfo.getUser().getNickname());
            } else {
                tvCenterName.setText(indexInfo.getUser().getMobile());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.rlay_personalInfo, R.id.rlay_auth_btn, R.id.rlay_my_exam, R.id.rlay_quit_parter, R.id.rlay_star, R.id.rlay_service, R.id.rlay_site, R.id.rlay_setup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlay_personalInfo:
                break;
            case R.id.rlay_auth_btn:
                break;
            case R.id.rlay_my_exam:
                break;
            case R.id.rlay_quit_parter:
                break;
            case R.id.rlay_star:
                break;
            case R.id.rlay_service:
                break;
            case R.id.rlay_site:
                break;
            case R.id.rlay_setup:
                break;
        }
    }
}
