package com.example.ozner.hoyomvp.MyCenter.views.BluetoothManagent;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.ozner.hoyomvp.Utils.ByteUtil;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ozner_67 on 2016/10/21.
 * 邮箱：xinde.zhang@cftcn.com
 * <p>
 * 水卫士蓝牙管理器
 */

public class WaterBluetoothManager {
    private static final String TAG = "WaterBluetoothManager";
    private ReadSensorInfo sensorInfoListener;
    private ReadFilterInfo filterInfoListener;
    private ConnectState connectStateListener;

    Context mContext;
    BluetoothDevice blueDevice;//选中的蓝牙设备
    BluetoothGatt bluetoothGatt;
    BluetoothCallbackIMP bluetoothCallback;
    BluetoothGattCharacteristic mInput = null;
    BluetoothGattCharacteristic mOutput = null;

    public WaterBluetoothManager(Context context) {
        this.mContext = context;
        bluetoothCallback = new BluetoothCallbackIMP();
    }

    /**
     * 写入滤芯回调
     */
    public interface WriteFilterListener {
        void onResult(boolean isSuccess, String errMsg);

        void writingFilter(int index);
    }

    /**
     * 连接状态
     */
    public interface ConnectState {
        //连接中
        void onConnecting();

        //已连接
        void onConnected();

        //查找服务
        void onFindingService();

        //断开连接中
        void onDisConnecting();

        //断开连接
        void onDisConnected();
    }

    /**
     * 读取传感器信息接口
     */
    public interface ReadSensorInfo {
        void onReadSensor(SensorInfo sensorInfo);
    }

    /**
     * 读取滤芯信息接口
     */
    public interface ReadFilterInfo {
        void onReadFilter(HashMap<Integer, FilterInfo> filterInfo);
    }

    /**
     * 设置蓝牙连接状态回调
     *
     * @param connectStateListener
     */
    public void setConnectStateListener(ConnectState connectStateListener) {
        this.connectStateListener = connectStateListener;
    }

    /**
     * 设置读取传感器回调
     *
     * @param sensorListener
     */
    public void setReadSensorListener(ReadSensorInfo sensorListener) {
        this.sensorInfoListener = sensorListener;
    }

    /**
     * 设置读取滤芯回调
     *
     * @param filterInfoListener
     */
    public void setReadFilterListener(ReadFilterInfo filterInfoListener) {
        this.filterInfoListener = filterInfoListener;
    }

    /**
     * 连接设备
     *
     * @param device
     */
    public void connectDevice(BluetoothDevice device) {
        blueDevice = device;
        Log.e(TAG, "connectDevice: " + String.format("%s(%s)", blueDevice.getAddress(), "等待连接"));
        if (connectStateListener != null) {
            connectStateListener.onConnecting();
        }
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        }
        device.connectGatt(mContext, false, bluetoothCallback);
    }

    /**
     * 断开连接
     */
    public void disConnect() {
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        }
        blueDevice = null;
    }

    /**
     * 读取滤芯信息列表
     */
    public void readFilterInfo() {
        lastReadFilterIndex = 0;
        readFilter(0);
    }


    private Thread writeThread;

    /**
     * 写入滤芯信息
     *
     * @param filterInfos
     */
    public void writeFilterInfos(final FilterInfo[] filterInfos, final WriteFilterListener listener) {
        //判断蓝牙连接
        if (bluetoothGatt == null) {
            listener.onResult(false, "蓝牙未连接");
            return;
        }
        if (!isConnected) {//设备没有连接
            Log.e(TAG, "writeFilterInfos: 设备没有连接");
            listener.onResult(false, "设备未连接");
            return;
        }
        if (writeThread != null && !writeThread.isInterrupted()) {
            writeThread.interrupt();
        }

        writeThread = null;
        writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < filterInfos.length; i++) {
                        byte[] data = new byte[19];
                        data[0] = 0x12;
                        filterInfos[i].toBytes(data, 1);
                        if (mInput != null) {
                            mInput.setValue(data);
                            listener.writingFilter(filterInfos[i].index);
                            while (!bluetoothGatt.writeCharacteristic(mInput)) {
                                Log.e(TAG, String.format("%s(写入滤芯%d错误)", blueDevice.getAddress(), filterInfos[i].index));
                            }
                        }
                        //延时防止写入太快设备收不到
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.e(TAG, "writeFilterInfos_sleep_ex: " + e.getMessage());
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.e(TAG, "writeFilterInfos_Ex: " + ex.getMessage());
                    listener.onResult(false, ex.getMessage());
                    return;
                }
                listener.onResult(true, "写入完成");
            }
        });
        writeThread.start();
    }


    /**
     * 读取传感器信息
     */
    public void readSensorInfo() {
        if (bluetoothGatt == null) return;
        if (!isConnected) return;

        lastReadFilterIndex = 0;

        Log.e(TAG, "readSensor: " + String.format("%s(读取传感器)", blueDevice.getAddress()));
        if (mInput != null) {
            mInput.setValue(new byte[]{0x10});

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bluetoothGatt.writeCharacteristic(mInput);
        } else {
            Log.e(TAG, "readSensor: mInput 为空");
        }
    }

    private void readFilter(int index) {
        if (bluetoothGatt == null) {
            Log.e(TAG, "readFilter:bluetoothGatt is null");
            return;
        }
        if (!isConnected) {
            Log.e(TAG, "readFilter: 设备没有连接");
            return;
        }
        Log.e(TAG, "readFilter: " + String.format("%s(读取滤芯:%d)", blueDevice.getAddress(), index));
        if (mInput != null) {
            mInput.setValue(new byte[]{0x11, (byte) index});
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bluetoothGatt.writeCharacteristic(mInput);
        } else {
            Log.e(TAG, "readFilter: mInput 为空");
        }
    }


    private Handler handler = new Handler();

    static UUID GetUUID(int id) {
        return UUID.fromString(String.format(
                "%1$08x-0000-1000-8000-00805f9b34fb", id));
    }

    private boolean isConnected = false;
    //    private List<FilterInfo> filterInfos = new ArrayList<>();
    private HashMap<Integer, FilterInfo> filterInfos = new HashMap<>();
    int lastReadFilterIndex = 0;

    class BluetoothCallbackIMP extends BluetoothGattCallback {
        private static final int ServiceId = 0xFFF0;

        BluetoothGattService mService = null;

        final UUID Characteristic_Input = GetUUID(0xFFF2);
        final UUID Characteristic_Output = GetUUID(0xFFF1);
        final UUID GATT_CLIENT_CHAR_CFG_UUID = GetUUID(0x2902);

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            bluetoothGatt = gatt;
            if (gatt.getDevice() != blueDevice) return;
            switch (newState) {
                case BluetoothGatt.STATE_CONNECTING:
                    if (connectStateListener != null) {
                        connectStateListener.onConnecting();
                    }
                    Log.e(TAG, "onConnectionStateChange: " + String.format("%s(%s)", blueDevice.getAddress(), "连接中"));
                    break;
                case BluetoothGatt.STATE_CONNECTED:
                    Log.e(TAG, "onConnectionStateChange: " + String.format("%s(%s)", blueDevice.getAddress(), "查找服务"));
                    if (connectStateListener != null) {
                        connectStateListener.onFindingService();
                    }
                    gatt.discoverServices();
                    break;
                case BluetoothGatt.STATE_DISCONNECTING:
                    isConnected = false;
                    if (connectStateListener != null) {
                        connectStateListener.onDisConnecting();
                    }
                    Log.e(TAG, "onConnectionStateChange: " + String.format("%s(%s)", blueDevice.getAddress(), "断开中"));
                    break;
                case BluetoothGatt.STATE_DISCONNECTED:
                    isConnected = false;
                    if (connectStateListener != null) {
                        connectStateListener.onDisConnected();
                    }
                    Log.e(TAG, "onConnectionStateChange: " + String.format("%s(%s)", blueDevice.getAddress(), "已断开"));
                    break;
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            byte[] bytes = characteristic.getValue();
            if (bytes.length > 0) {
                switch (bytes[0]) {
                    case (byte) 0xa0: {//返回传感器信息
                        final SensorInfo sensorInfo = new SensorInfo();
                        sensorInfo.loadFromBytes(bytes, 1);
                        Log.e(TAG, "onCharacteristicChanged: " + String.format("TDS 进水:%d 原始:%d 出水:%d 原始:%d\n水流量:%d升",
                                sensorInfo.tds_in, sensorInfo.tds_in_raw,
                                sensorInfo.tds_out, sensorInfo.tds_out_raw, sensorInfo.vol));

                        if (sensorInfoListener != null) {
                            sensorInfoListener.onReadSensor(sensorInfo);
                        }
                        readFilterInfo();
                    }
                    break;
                    //返回滤芯信息
                    case (byte) 0xa1:
                        FilterInfo fi = new FilterInfo();
                        fi.loadFromBytes(bytes, 1);
                        if (fi.index >= 0 && fi.index < 5) {
                            if (fi.time != 0) {
                                filterInfos.put((int) fi.index, fi);
                            }
                        }
                        Log.e(TAG, "onCharacteristicChanged: 读取滤芯----" + fi.index + ",时间：" + fi.time + ",工作时间:" + fi.workTime + ",最大时间：" + fi.maxTime + ",最大流量：" + fi.maxVol);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (filterInfoListener != null) {
                                    Log.e(TAG, "onCharacteristicChanged: filterInfoListener");
                                    filterInfoListener.onReadFilter(filterInfos);
                                }
                            }
                        });
                        lastReadFilterIndex++;
                        if (lastReadFilterIndex < 5) {
                            readFilter(lastReadFilterIndex);
                        } else
                            lastReadFilterIndex = 0;
                        break;
                }
            }
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            if (gatt.getDevice() != blueDevice) return;
            Log.e(TAG, "onDescriptorWrite: " + String.format("%s(%s)", blueDevice.getAddress(), "设置通知成功"));
            isConnected = true;

            if (connectStateListener != null) {
                connectStateListener.onConnected();
            }
            readSensorInfo();
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);

            if (gatt.getDevice() != blueDevice) return;
            Log.e(TAG, "onServicesDiscovered: " + String.format("%s(%s)", blueDevice.getAddress(), "设置设备"));
            mService = gatt.getService(GetUUID(ServiceId));
            if (mService != null) {
                mInput = mService.getCharacteristic(Characteristic_Input);
                mOutput = mService.getCharacteristic(Characteristic_Output);
                mInput.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);

                BluetoothGattDescriptor desc = mOutput.getDescriptor(GATT_CLIENT_CHAR_CFG_UUID);
                if (desc != null) {
                    Log.e(TAG, "onServicesDiscovered: " + String.format("%s(%s)", blueDevice.getAddress(), "设置通知"));
                    desc.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    bluetoothGatt.setCharacteristicNotification(mOutput, true);
                    if (!bluetoothGatt.writeDescriptor(desc)) {
                        Log.e(TAG, "onServicesDiscovered: " + String.format("%s(%s)", blueDevice.getAddress(), "设置通知失败"));
                    }
                }
            } else {
                Log.e(TAG, "onServicesDiscovered: " + String.format("%s(%s)", blueDevice.getAddress(), "未发现服务"));

            }
        }
    }

    /**
     * 传感器信息
     */
    public static class SensorInfo {
        public int tds_in;
        public int tds_in_raw;
        public int tds_out;
        public int tds_out_raw;
        public int vol;

        public void loadFromBytes(byte[] bytes, int index) {
            tds_in_raw = ByteUtil.getShort(bytes, index);
            tds_in = ByteUtil.getShort(bytes, index + 2);
            tds_out_raw = ByteUtil.getShort(bytes, index + 4);
            tds_out = ByteUtil.getShort(bytes, index + 6);
            vol = ByteUtil.getShort(bytes, index + 8);
        }

    }

    /**
     * 滤芯信息
     */
    public static class FilterInfo {
        public byte index;
        public byte rev;

        public long time;
        public int workTime;
        public int maxTime;
        public int maxVol;

        public void loadFromBytes(byte[] bytes, int index) {
            this.index = bytes[index];
            rev = bytes[index + 1];
            time = ByteUtil.getInt(bytes, index + 2);
            workTime = ByteUtil.getInt(bytes, index + 6);
            maxTime = ByteUtil.getInt(bytes, index + 10);
            maxVol = ByteUtil.getInt(bytes, index + 14);

        }

        public void toBytes(byte[] bytes, int startIndex) {
            bytes[startIndex] = index;
            bytes[startIndex + 1] = 0;
            ByteUtil.putInt(bytes, (int) time, startIndex + 2);
            ByteUtil.putInt(bytes, 0, startIndex + 6);
            ByteUtil.putInt(bytes, maxTime, startIndex + 10);
            ByteUtil.putInt(bytes, maxVol, startIndex + 14);
        }

    }
}
