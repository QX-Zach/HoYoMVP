package com.example.ozner.hoyomvp.Manager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ozner.hoyomvp.BaseFragment;
import com.example.ozner.hoyomvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager, null, false);
//        TextView textView = (TextView) view.findViewById(R.id.text);
//        textView.setText(getTitle());
        return view;
    }

}
