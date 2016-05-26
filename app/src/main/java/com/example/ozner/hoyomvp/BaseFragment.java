package com.example.ozner.hoyomvp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ozner.hoyomvp.Bean.FagmentListener;


public class BaseFragment extends Fragment {
    protected FagmentListener fagmentListener;
    private String title;
    private int iconId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public void onAttach(Context context) {
        fagmentListener = (FagmentListener) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null, false);
//        TextView textView = (TextView) view.findViewById(R.id.text);
//        textView.setText(getTitle());
        return view;
    }
}
