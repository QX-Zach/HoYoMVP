package com.example.ozner.hoyomvp.MyCenter.views.BluetoothManagent;

import android.annotation.SuppressLint;

import java.util.Arrays;
import java.util.Date;

public class BluetoothScanResponse {
    /**
     * 型号
     */
    public String Model = "";


    //private int ServiceId;
    /**
     * 固件版本
     */
    public Date Firmware;

    /**
     * 主板硬件平台
     */
    public String MainbroadPlatform;

    /**
     * 主板固件版本
     */
    public Date MainbroadFirmware;

    /**
     * 硬件平台
     */
    public String Platform;
    /**
     * 自定义数据类型
     */
    public int ScanResponseType;
    /**
     * 自定义数据长度
     */
    public int CustomDataLength;
    /**
     * 自定义数据 最大8个字节
     */
    public byte[] ScanResponseData;
    //public boolean Available;

    public BluetoothScanResponse() {
    }

//	private String byteToStr(byte t)
//	{
//		return String.format("%1$,02d",t);
//	}

    @SuppressLint("NewApi")
    public void FromBytes(byte[] data) {
        //ServiceId=ByteUtil.getShort(data, 0);
        Platform = new String(data, 0, 3);
        Model = new String(data, 3, 6).trim();
        Firmware = new Date(data[9] + 2000 - 1900, data[10] - 1, data[11], data[12],
                data[13], data[14]);

        ScanResponseType = data[15];
        //CustomDataLength = data[18];
        ScanResponseData = Arrays.copyOfRange(data, 17, data.length-1);
        //Available = data[27] != 0;

    }
}
