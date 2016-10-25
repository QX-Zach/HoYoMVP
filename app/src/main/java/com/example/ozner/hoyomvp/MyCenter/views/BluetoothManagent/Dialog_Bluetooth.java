package com.example.ozner.hoyomvp.MyCenter.views.BluetoothManagent;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ozner.hoyomvp.R;

import java.util.Arrays;


/**
 * Created by zhiyongxu on 2016/10/9.
 */
public class Dialog_Bluetooth extends Dialog implements BluetoothAdapter.LeScanCallback, AdapterView.OnItemClickListener {
    private static final String TAG = "Dialog_Bluetooth";
    BluetoothAdapter bluetoothAdapter;
    BluetoothManager bluetoothManager;
    MyAdapter myAdapter;
    BluetoothDevice selectedDevice = null;

    final static byte GAP_ADTYPE_MANUFACTURER_SPECIFIC = (byte) 0xff;
    final static byte GAP_ADTYPE_SERVICE_DATA = 0x16;
    final short Service_UUID = (short) 0xFFF0;

    private void init() {
        bluetoothManager = (BluetoothManager) this.getContext().getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        myAdapter = new MyAdapter(getContext());
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View view = inflater.inflate(R.layout.dialog_bluetooth, null);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        setTitle("正在搜索设备...");

        if (!bluetoothAdapter.isEnabled()) {
            setTitle("请打开蓝牙");
        }

        ListView listView = (ListView) findViewById(R.id.deviceList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        /*Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        DisplayMetrics d = this.getContext().getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);*/
    }

    public Dialog_Bluetooth(Context context) {
        super(context);
        init();
    }

    boolean isLeScan = false;

    @Override
    public void dismiss() {
        Log.e(TAG, "dismiss: ==================");
        if (isLeScan) {
            bluetoothAdapter.stopLeScan(this);
            isLeScan = false;
        }
        super.dismiss();
    }


    @Override
    public void show() {
        super.show();
        if (bluetoothAdapter.isEnabled()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!bluetoothAdapter.startLeScan(Dialog_Bluetooth.this)) {
                        Log.e("Ozner", "startLeScan Fail");
                    }
                }
            }).start();
        }
    }

    public BluetoothDevice getSelectedDevice() {
        return selectedDevice;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedDevice = myAdapter.getItem(position);
        this.dismiss();
    }

    class MyAdapter extends ArrayAdapter<BluetoothDevice> {
        LayoutInflater layoutInflater;

        public MyAdapter(Context context) {
            super(context, R.layout.list_item_device);
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = layoutInflater.inflate(R.layout.list_item_device, null);
            }
            BluetoothDevice device = getItem(position);
            TextView nameView = (TextView) view.findViewById(R.id.name);
            nameView.setText(device.getName());

            TextView macView = (TextView) view.findViewById(R.id.mac);
            macView.setText(device.getAddress());
            return view;
        }

    }

    public Dialog_Bluetooth(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        byte[] service_data = null;

        int pos = 0;
        while (pos < scanRecord.length) {
            try {
                int len = scanRecord[pos++];
                if (len > 0) {
                    byte flag = scanRecord[pos];
                    if (len > 1) {
                        switch (flag) {
                            case GAP_ADTYPE_SERVICE_DATA: {
                                int uuid = (short) (scanRecord[pos + 2] << 16) + scanRecord[pos + 1];
                                if (uuid == Service_UUID) {
                                    service_data = Arrays.copyOfRange(scanRecord, pos + 3, pos + len - 1);
                                }
                            }
                            break;
                        }

                    }
                }
                pos += len;
                if (pos >= scanRecord.length)
                    break;
            } catch (Exception e) {
                return;
            }
        }
        if (service_data != null) {
            BluetoothScanResponse response = new BluetoothScanResponse();
            response.FromBytes(service_data);
            if (response.Model.equals("WG-001")) {
                if (myAdapter.getPosition(device) < 0) {
                    myAdapter.add(device);
                    myAdapter.notifyDataSetInvalidated();
                }
            }
        }
    }
}
