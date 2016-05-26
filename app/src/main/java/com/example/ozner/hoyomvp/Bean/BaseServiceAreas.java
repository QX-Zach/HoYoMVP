package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by ozner_67 on 2016/4/18.
 */
public class BaseServiceAreas implements Serializable {

    /**
     * id : 44
     * GroupNumber : 68213
     * Province : 黑龙江省
     * City : 齐齐哈尔市
     * Country : 龙沙区
     * Address : null
     * lat : 47.301073163863
     * lng : 123.94483825767
     * CreateTime : /Date(1460446327303)/
     */

    private int id;
    private int GroupNumber;
    private String Province;
    private String City;
    private String Country;
    private Object Address;
    private double lat;
    private double lng;
    private String CreateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupNumber() {
        return GroupNumber;
    }

    public void setGroupNumber(int GroupNumber) {
        this.GroupNumber = GroupNumber;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public Object getAddress() {
        return Address;
    }

    public void setAddress(Object Address) {
        this.Address = Address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getCreateTime() {
//        return CreateTime;
        try {
            return Long.parseLong(CreateTime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.CreateTime = Long.parseLong(CreateTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.CreateTime = 0;
//        }
    }
}
