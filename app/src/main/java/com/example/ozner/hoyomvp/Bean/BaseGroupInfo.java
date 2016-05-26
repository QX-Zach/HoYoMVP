package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by ozner_67 on 2016/3/25.
 * 组详细资料
 */
public class BaseGroupInfo implements Serializable {
    private int GroupNumber;
    private String GroupName;
    private String Province;
    private String City;
    private String Country;
    private String Address;
    private String lat;
    private String lng;
    private String CreateTime;
    private String ModifyTime;

    public void setGroupNumber(int groupNumber) {
        this.GroupNumber = groupNumber;
    }

    public int getGroupNumber() {
        return this.GroupNumber;
    }

    public void setGroupName(String groupName) {
        this.GroupName = groupName;
    }

    public String getGroupName() {
        return this.GroupName;
    }

    public void setProvince(String province) {
        this.Province = province;
    }

    public String getProvince() {
        return this.Province;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getCity() {
        return this.City;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public String getCountry() {
        return this.Country;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return this.lng;
    }

    public void setCreateTime(String createTime) {
        this.CreateTime = createTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.CreateTime = Long.parseLong(createTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.CreateTime = 0;
//        }
    }

    public long getCreateTime() {
//        return this.CreateTime;
        try {
            return Long.parseLong(CreateTime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public void setModifyTime(String modifyTime) {
        this.ModifyTime = modifyTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.ModifyTime = Long.parseLong(modifyTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.ModifyTime = 0;
//        }
    }

    public long getModifyTime() {
//        return this.ModifyTime;
        try {
            return Long.parseLong(ModifyTime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
