package com.example.ozner.hoyomvp.Message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ozner.hoyomvp.BaseFragment;
import com.example.ozner.hoyomvp.Utils.LogUtilLC;
import com.example.ozner.hoyomvp.R;

public class MessageFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null, false);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(LogUtilLC.APP_DBG){
            LogUtilLC.E("tag","MessageFragment_onActivityCreated");
        }
        super.onActivityCreated(savedInstanceState);
    }
}
