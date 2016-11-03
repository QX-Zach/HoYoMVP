package com.example.ozner.hoyomvp.MyCenter.views;

import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ozner.hoyomvp.MyCenter.views.BluetoothManagent.Dialog_Bluetooth;
import com.example.ozner.hoyomvp.MyCenter.views.BluetoothManagent.WaterBluetoothManager;
import com.example.ozner.hoyomvp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WaterGuardActivity extends AppCompatActivity {
    private static final String TAG = "WaterGuardActivity";
    WaterBluetoothManager waterManager;
    @InjectView(R.id.tv_show)
    TextView tvShow;
    @InjectView(R.id.tv_connectState)
    TextView tvConnectState;
    @InjectView(R.id.tv_senor)
    TextView tvSenor;
    @InjectView(R.id.btn_connectDevice)
    Button btnConnectDevice;
    @InjectView(R.id.btn_readSensor)
    Button btnReadSensor;
    @InjectView(R.id.btn_readFilter)
    Button btnReadFilter;
    @InjectView(R.id.btn_disConnect)
    Button btnDisConnect;
    @InjectView(R.id.btn_writeTest)
    Button btnWriteTest;
    @InjectView(R.id.tv_writeInfo)
    TextView tvWriteInfo;
    private List<WaterBluetoothManager.FilterInfo> filterInfos;
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_guard);
        ButterKnife.inject(this);
        initActionBar();
        waterManager = new WaterBluetoothManager(this);
        waterManager.setConnectStateListener(new ConnectStateImp());
        waterManager.setReadSensorListener(new WaterBluetoothManager.ReadSensorInfo() {
            @Override
            public void onReadSensor(final WaterBluetoothManager.SensorInfo sensorInfo) {
                Log.e("tag", "onReadSensor:" + String.format("TDS 进水:%d 原始:%d 出水:%d 原始:%d\n水流量:%d升",
                        sensorInfo.tds_in, sensorInfo.tds_in_raw,
                        sensorInfo.tds_out, sensorInfo.tds_out_raw, sensorInfo.vol));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSenor.setText(String.format("TDS 进水:%d 原始:%d 出水:%d 原始:%d\n水流量:%d升",
                                sensorInfo.tds_in, sensorInfo.tds_in_raw,
                                sensorInfo.tds_out, sensorInfo.tds_out_raw, sensorInfo.vol));
                    }
                });
            }
        });

        waterManager.setReadFilterListener(new WaterBluetoothManager.ReadFilterInfo() {
            @Override
            public void onReadFilter(final HashMap<Integer, WaterBluetoothManager.FilterInfo> filterInfos) {
                Log.e("tag", "onReadFilter: " + filterInfos.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("滤芯数量:%d\n", filterInfos.size()));
                        Set<Integer> keySet = filterInfos.keySet();
                        for (int key : keySet) {
                            sb.append("\nindex:" + filterInfos.get(key).index);
                            sb.append(String.format("\n上次更换时间:%s\n", fmt.format(new Date(filterInfos.get(key).time * 1000))));
                            sb.append("工作时间:" + filterInfos.get(key).workTime + "分钟\n");
                            sb.append(String.format("最大工作时间:%d分钟\n", filterInfos.get(key).maxTime));
                            sb.append(String.format("最大工作流量:%d升\n", filterInfos.get(key).maxVol));
                        }
                        tvShow.setText(sb.toString());
                    }
                });
            }
        });
    }

    private void initActionBar() {
        ActionBar bar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= 21) {
            bar.setElevation(0);
        }
        TextView titleView = new TextView(this);
        titleView.setTextColor(Color.WHITE);
        titleView.setTextSize(18);
        titleView.setText("水卫士");
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        bar.setCustomView(titleView, lp);
        bar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_connectDevice, R.id.btn_readSensor, R.id.btn_readFilter, R.id.btn_disConnect, R.id.btn_writeTest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connectDevice:
                tvConnectState.setText("");
                Dialog_Bluetooth dialog = new Dialog_Bluetooth(this);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        BluetoothDevice device = ((Dialog_Bluetooth) dialog).getSelectedDevice();
                        if (device != null) {
                            waterManager.connectDevice(device);
                        }
                    }
                });
                break;
            case R.id.btn_readSensor:
                tvSenor.setText("");
                waterManager.readSensorInfo();
                break;
            case R.id.btn_readFilter:
                tvShow.setText("");
                waterManager.readFilterInfo();
                break;
            case R.id.btn_disConnect:
                waterManager.disConnect();
                break;
            case R.id.btn_writeTest:
                writeFilterTest();
                break;
        }
    }


    private void writeFilterTest() {
        tvWriteInfo.setText("");

        List<WaterBluetoothManager.FilterInfo> filterInfos = new ArrayList<>();
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < 5; i++) {
            WaterBluetoothManager.FilterInfo info = new WaterBluetoothManager.FilterInfo();
            info.index = (byte) i;
            info.time = (int) (new Date().getTime() / 1000);
            info.maxVol = random.nextInt(2000) + 1000;
            info.workTime = random.nextInt(1000);
            info.maxTime = random.nextInt(1000) + 1000;
            filterInfos.add(info);
        }
        for (WaterBluetoothManager.FilterInfo info : filterInfos) {
            String infoStr = String.format("index:%d, maxVol:%d, workTime:%d, maxtTime:%d"
                    , (int) info.index, info.maxVol, info.workTime, info.maxTime);
            Log.e(TAG, "writeFilterTest_data: " + infoStr);
            tvWriteInfo.append(infoStr);
            tvWriteInfo.append("\n");
        }
        WaterBluetoothManager.FilterInfo[] writeInfos = new WaterBluetoothManager.FilterInfo[filterInfos.size()];
        filterInfos.toArray(writeInfos);
        waterManager.writeFilterInfos(writeInfos, new WaterBluetoothManager.WriteFilterListener() {
            @Override
            public void onResult(boolean isSuccess, String errMsg) {
                Log.e(TAG, "writeFilter_result: " + isSuccess + " ,ErrorMsg:" + errMsg);
            }

            @Override
            public void writingFilter(int index) {
                Log.i(TAG, "writingFilter_index: 正在写入：" + index);
            }
        });
    }


    private void setConnectState(final String stateMsg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvConnectState.append(stateMsg + "\n");
            }
        });
    }

    class ConnectStateImp implements WaterBluetoothManager.ConnectState {

        @Override
        public void onConnecting() {
            setConnectState("等待连接");
        }

        @Override
        public void onConnected() {
            setConnectState("已连接");
        }

        @Override
        public void onFindingService() {
            setConnectState("查找服务");
        }

        @Override
        public void onDisConnecting() {
            setConnectState("正在断开连接");
        }

        @Override
        public void onDisConnected() {
            setConnectState("已断开");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        waterManager.disConnect();
    }
}
