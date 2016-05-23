package com.example.ozner.hoyomvp.Utils;

/**
 * Created by xinde on 2016/3/11.
 * 订单 服务名称和服务图片
 */
public class ServiceTypeItem {
    private String serviceName;
    private int imgId;

    public ServiceTypeItem(){}
    public ServiceTypeItem(String serviceName, int imgId) {
        this.serviceName = serviceName;
        this.imgId = imgId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgId() {
        return this.imgId;
    }
}
