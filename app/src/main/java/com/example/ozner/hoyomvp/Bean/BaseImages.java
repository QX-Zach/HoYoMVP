package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xinde on 2016/3/15.
 */
public class BaseImages implements Serializable {
    private int id;
    private String imageName;
    private String imageUrl;
    private String type;
    private String action;
    private int userId;
    private String orderId;
    private String createTime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setUserId(int userid) {
        this.userId = userid;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.createTime = Long.parseLong(createTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            this.createTime = 0;
//        }
    }

    public String getCreateTime() {
        return this.createTime;
    }
}
