package com.example.ozner.hoyomvp.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ozner.hoyomvp.BaseActivity;
import com.example.ozner.hoyomvp.Login.Presenter.LoginPresenter;
import com.example.ozner.hoyomvp.Login.Presenter.RegistPresenter;
import com.example.ozner.hoyomvp.Login.views.IRegistView;
import com.example.ozner.hoyomvp.Login.views.IloginView;
import com.example.ozner.hoyomvp.MainActivity;
import com.example.ozner.hoyomvp.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IloginView, IRegistView, RadioGroup.OnCheckedChangeListener {

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
    @InjectView(R.id.llay_reg_step_one)
    LinearLayout llayRegStepOne;
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
    LoginPresenter loginPresenter;
    RegistPresenter registPresenter;
    @InjectView(R.id.reg_tv_two_mobile)
    TextView regTvTwoMobile;

    private int timeIndex = 0;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        loginPresenter = new LoginPresenter(this, this);
        registPresenter = new RegistPresenter(this, this);
        initListener();
        loginPresenter.reLogin(this);
    }

    private void initListener() {
        rgLabelSel.setOnCheckedChangeListener(this);

        cbProtocol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && regEtOneMobile.getText().length() == 11) {
                    btnRegStepOne.setEnabled(true);
                } else {
                    btnRegStepOne.setEnabled(false);
                }
            }
        });

        regEtOneMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (cbProtocol.isChecked() && s.length() == 11) {
                    btnRegStepOne.setEnabled(true);
                } else {
                    btnRegStepOne.setEnabled(false);
                }
            }
        });

        regEtTwoVerify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    btnRegStepTwo.setEnabled(true);
                } else {
                    btnRegStepTwo.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.btn_reg_step_one, R.id.btn_reg_step_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.login(LoginActivity.this);
                break;
            case R.id.btn_reg_step_one:
                registPresenter.sendPhoneCode();
                break;
            case R.id.btn_reg_step_two:
                registPresenter.checkVerifyCode();
                break;
            case R.id.btn_reg_step_three:
                registPresenter.registSubmit(LoginActivity.this);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_login:
                llayRegister.setVisibility(View.GONE);
                llayLogin.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_register:
                llayRegister.setVisibility(View.VISIBLE);
                llayLogin.setVisibility(View.GONE);

                llayRegStepOne.setVisibility(View.VISIBLE);
                llayRegStepTwo.setVisibility(View.GONE);
                llayRegStepThree.setVisibility(View.GONE);
                break;
        }
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
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        LoginActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        loginPresenter.cancleLogin();
        super.onBackPressed();
    }

    @Override
    public String getRegistMobile() {
        return regEtOneMobile.getText().toString().trim();
    }

    @Override
    public String getVerifyCode() {
        return regEtTwoVerify.getText().toString();
    }

    @Override
    public void goRegStepTwo() {
        showRegStepTwo();
        stopWaitVerifyCode();
        startWaitVerifyCode();
    }

    @Override
    public void goRegStepThree() {
        showRegStepThree();
    }

    @Override
    public String getRegisterName() {
        return regEtName.getText().toString().trim();
    }

    @Override
    public String getRegisterIDNo() {
        return regEtCard.getText().toString().trim();
    }

    @Override
    public String getRegisterPassword() {
        return regEtThreePass.getText().toString().trim();
    }

    @Override
    public String getRegisterInviteCode() {
        return regEtThreeInvite.getText().toString().trim();
    }

    @Override
    public void registSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        LoginActivity.this.finish();
    }

    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    private void startWaitVerifyCode() {
        if (timer == null)
            timer = new Timer();
        else {
            timer = null;
            timer = new Timer();
        }
        if (task != null) {
            task.cancel();
            task = new TimerTask() {

                @Override
                public void run() {
                    // 需要做的事:发送消息
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            };
        } else {
            task = new TimerTask() {

                @Override
                public void run() {
                    // 需要做的事:发送消息
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            };
        }
        timer.schedule(task, 0, 1000);
    }

    private void stopWaitVerifyCode() {
        timeIndex = 0;
        task.cancel();
        timer = null;
        tvWaitTime.setText("");
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (timeIndex < 60) {
                    timeIndex++;
                    String waitTime = String.valueOf(60 - timeIndex);
                    tvWaitTime.setText(waitTime);
                } else {
                    stopWaitVerifyCode();
                }
            }
            super.handleMessage(msg);
        }
    };

    //显示注册第二步,校验验证码
    private void showRegStepTwo() {
        llayRegStepOne.setVisibility(View.GONE);
        llayRegStepTwo.setVisibility(View.VISIBLE);
        llayRegStepThree.setVisibility(View.GONE);
        regTvTwoMobile.setText(regEtOneMobile.getText().toString().trim());
        regEtTwoVerify.setText("");
    }

    //显示注册第三部，提交注册信息
    private void showRegStepThree() {
        llayRegStepOne.setVisibility(View.GONE);
        llayRegStepTwo.setVisibility(View.GONE);
        llayRegStepThree.setVisibility(View.VISIBLE);
    }
}
