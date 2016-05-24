package com.example.ozner.hoyomvp.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ozner.hoyomvp.BaseActivity;
import com.example.ozner.hoyomvp.Bean.IBaseView;
import com.example.ozner.hoyomvp.Login.Presenter.LoginPresenter;
import com.example.ozner.hoyomvp.Login.views.IloginView;
import com.example.ozner.hoyomvp.MainActivity;
import com.example.ozner.hoyomvp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IloginView, IBaseView {

    @InjectView(R.id.rb_login)
    RadioButton rbLogin;
    @InjectView(R.id.rb_register)
    RadioButton rbRegister;
    @InjectView(R.id.rg_label_sel)
    RadioGroup rgLabelSel;
    @InjectView(R.id.login_et_phone)
    EditText loginEtPhone;
    @InjectView(R.id.login_et_passwd)
    EditText loginEtPasswd;
    @InjectView(R.id.tv_forget)
    TextView tvForget;
    @InjectView(R.id.llay_login)
    LinearLayout llayLogin;
    @InjectView(R.id.reg_et_one_mobile)
    EditText regEtOneMobile;
    @InjectView(R.id.btn_reg_step_one)
    Button btnRegStepOne;
    @InjectView(R.id.cb_protocol)
    CheckBox cbProtocol;
    @InjectView(R.id.tv_protocol)
    TextView tvProtocol;
    @InjectView(R.id.llay_reg_step_one)
    LinearLayout llayRegStepOne;
    @InjectView(R.id.reg_et_two_mobile)
    EditText regEtTwoMobile;
    @InjectView(R.id.reg_et_two_verify)
    EditText regEtTwoVerify;
    @InjectView(R.id.tv_waitTime)
    TextView tvWaitTime;
    @InjectView(R.id.btn_reg_step_two)
    Button btnRegStepTwo;
    @InjectView(R.id.llay_reg_step_two)
    LinearLayout llayRegStepTwo;
    @InjectView(R.id.reg_et_name)
    EditText regEtName;
    @InjectView(R.id.reg_et_card)
    EditText regEtCard;
    @InjectView(R.id.reg_et_three_pass)
    EditText regEtThreePass;
    @InjectView(R.id.reg_et_three_invite)
    EditText regEtThreeInvite;
    @InjectView(R.id.llay_reg_step_three)
    LinearLayout llayRegStepThree;
    @InjectView(R.id.llay_register)
    LinearLayout llayRegister;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.btn_reg_step_three)
    Button btnRegStepThree;

    LoginPresenter loginPresenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        loginPresenter = new LoginPresenter(this, this);
    }


    @Override
    public String getLoginMobile() {
        return loginEtPhone.getText().toString().trim();
    }

    @Override
    public String getLoginPassword() {
        return loginEtPasswd.getText().toString().trim();
    }

    @Override
    public void showLoading(String msg) {
        dialog = ProgressDialog.show(LoginActivity.this, null, msg);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void hiddeLoading() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        LoginActivity.this.finish();
    }

    @Override
    public void showFailedErr(String msg) {
        Toast.makeText(LoginActivity.this, "登录失败：" + msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_login, R.id.btn_reg_step_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.login();
                break;
            case R.id.btn_reg_step_three:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.cancleLogin();
    }
}
