package com.example.ozner.hoyomvp.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.ozner.hoyomvp.Bean.LogUtilLC;
import com.example.ozner.hoyomvp.Bean.NetJsonResponse;
import com.example.ozner.hoyomvp.HoYoApplication;
import com.example.ozner.hoyomvp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

}
