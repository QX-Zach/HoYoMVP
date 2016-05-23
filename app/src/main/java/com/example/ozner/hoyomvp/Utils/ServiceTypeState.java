package com.example.ozner.hoyomvp.Utils;

import com.example.ozner.hoyomvp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinde on 2016/3/11.
 * 订单服务类型工具类
 */
public class ServiceTypeState {

    //    private static Map<Integer, String> ServiceStateMap = new HashMap<>();
    private static Map<Integer, ServiceTypeItem> ServiceMap = new HashMap<>();

    static {
        ServiceMap.put(111010001, new ServiceTypeItem("送修", R.drawable.order_repair));
        ServiceMap.put(111010002, new ServiceTypeItem("整机维护", R.drawable.order_repair));
        ServiceMap.put(111010003, new ServiceTypeItem("不出水", R.drawable.order_full));
        ServiceMap.put(111010004, new ServiceTypeItem("不显示", R.drawable.order_install));
        ServiceMap.put(111010005, new ServiceTypeItem("漏水", R.drawable.order_full));
        ServiceMap.put(111010006, new ServiceTypeItem("出水异味", R.drawable.order_full));

        ServiceMap.put(123000001, new ServiceTypeItem("退货", R.drawable.order_refund));
        ServiceMap.put(123000002, new ServiceTypeItem("换货", R.drawable.order_change_pro));
        ServiceMap.put(123000003, new ServiceTypeItem("移机", R.drawable.order_move));
        ServiceMap.put(123000004, new ServiceTypeItem("充水", R.drawable.order_full));
        ServiceMap.put(123000005, new ServiceTypeItem("整改", R.drawable.order_repair));
        ServiceMap.put(123000006, new ServiceTypeItem("安装", R.drawable.order_install));
        ServiceMap.put(123000007, new ServiceTypeItem("维修维护", R.drawable.order_repair));
        ServiceMap.put(123000008, new ServiceTypeItem("换芯", R.drawable.order_changefilter));
        ServiceMap.put(123000009, new ServiceTypeItem("送货", R.drawable.order_delivery));
        ServiceMap.put(123000010, new ServiceTypeItem("水质检测", R.drawable.order_full));
        ServiceMap.put(123000011, new ServiceTypeItem("现场勘测", R.drawable.order_repair));

        ServiceMap.put(125010000, new ServiceTypeItem("上门", R.drawable.order_install));
        ServiceMap.put(125020000, new ServiceTypeItem("寄送服务", R.drawable.order_delivery));
        ServiceMap.put(125030000, new ServiceTypeItem("远程指导", R.drawable.order_repair));
        ServiceMap.put(125040000, new ServiceTypeItem("送装一体", R.drawable.order_install));
    }


    public static String getServiceName(int state) {
        if (ServiceMap.containsKey(state)) {
            return ServiceMap.get(state).getServiceName();
        } else {
            return "";
        }
    }

    public static int getServiceImgId(int state) {
        if (ServiceMap.containsKey(state)) {
            return ServiceMap.get(state).getImgId();
        } else {
            return R.drawable.order_install;
        }
    }

    public static ServiceTypeItem getService(int state) {
        if (ServiceMap.containsKey(state)) {
            return ServiceMap.get(state);
        } else {
            return new ServiceTypeItem(String.valueOf(state), R.drawable.order_install);
        }
    }

}
