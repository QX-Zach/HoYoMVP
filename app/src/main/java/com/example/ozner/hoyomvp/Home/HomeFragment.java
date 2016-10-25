package com.example.ozner.hoyomvp.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ozner.hoyomvp.BaseFragment;
import com.example.ozner.hoyomvp.MyCenter.views.WaterGuardActivity;
import com.example.ozner.hoyomvp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    @InjectView(R.id.btn_waterGuard)
    Button btnWaterGuard;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btn_waterGuard)
    public void onClick() {
        startActivity(new Intent(getContext(), WaterGuardActivity.class));
    }
}
