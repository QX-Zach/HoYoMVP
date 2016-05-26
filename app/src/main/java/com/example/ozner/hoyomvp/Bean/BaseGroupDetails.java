package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ozner_67 on 2016/4/18.
 */
public class BaseGroupDetails implements Serializable {

    @Override
    public String toString() {
        return "BaseGroupDetails{" +
                "ModifyTime='" + ModifyTime + '\'' +
                ", GroupNumber=" + GroupNumber +
                ", GroupName='" + GroupName + '\'' +
                ", MemberScope='" + MemberScope + '\'' +
                ", MemberState=" + MemberState +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", Country='" + Country + '\'' +
                ", Address='" + Address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }

    /**
     * GroupNumber : 68213
     * GroupName : 网点y
     * MemberScope : partner
     * MemberState : 70001
     * Province : 上海
     * City : 上海
     * Country : 浦东
     * Address : 桂桥路60号
     * lat : 31.246798924374
     * lng : 121.6113717577
     * CreateTime : /Date(1458890225290)/
     * ModifyTime : /Date(1458890225290)/
     */

    private int GroupNumber;
    private String GroupName;
    private String MemberScope;
    private int MemberState;
    private String Province;
    private String City;
    private String Country;
    private String Address;
    private double lat;
    private double lng;
    private String CreateTime;
    private String ModifyTime;
    private List<BaseServiceAreas> ServiceAreas;

    public List<BaseServiceAreas> getServiceAreas() {
        return ServiceAreas;
    }

    public void setServiceAreas(List<BaseServiceAreas> serviceAreas) {
        ServiceAreas = serviceAreas;
    }

    public int getGroupNumber() {
        return GroupNumber;
    }

    public void setGroupNumber(int GroupNumber) {
        this.GroupNumber = GroupNumber;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public String getMemberScope() {
        return MemberScope;
    }

    public void setMemberScope(String MemberScope) {
        this.MemberScope = MemberScope;
    }

    public int getMemberState() {
        return MemberState;
    }

    public void setMemberState(int MemberState) {
        this.MemberState = MemberState;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
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

    public long getModifyTime() {
//        return ModifyTime;
        try {
            return Long.parseLong(ModifyTime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public void setModifyTime(String ModifyTime) {
        this.ModifyTime = ModifyTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.ModifyTime = Long.parseLong(ModifyTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.ModifyTime = 0;
//        }
    }
}
