package com.example.ozner.hoyomvp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.example.ozner.hoyomvp.Utils.NetErrDecode;

/**
 * Created by ozner_67 on 2016/5/24.
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {
    private ProgressDialog dialog;

    @Override
    public void showLoading(String msg) {
        dialog = ProgressDialog.show(BaseActivity.this, null, msg);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
    }

    @Override
    public void hiddeLoading() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    @Override
    public void showErrMsg(int state) {
        NetErrDecode.ShowErrMsgDialog(BaseActivity.this, state, "未知错误");
    }

    @Override
    public void showToast(String msg) {
        Toast toast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void finishAll() {
        BaseActivity.this.finish();
    }

}
