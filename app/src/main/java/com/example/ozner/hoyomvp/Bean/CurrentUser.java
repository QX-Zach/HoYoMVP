package com.example.ozner.hoyomvp.Bean;
import java.io.Serializable;

/**
 * Created by xinde on 2016/3/3.
 */
public class CurrentUser implements Serializable {
    private int userid;
    private String openid;
    private String nickname;
    private String headimageurl;
    private String scope;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String mobile;
    private BaseGroupDetails GroupDetails;
    @Override
    public String toString() {
        return "CurrentUser{" +
                "headimageurl='" + headimageurl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", scope='" + scope + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", mobile='" + mobile + '\'' +
                ", GroupDetails=" + GroupDetails.toString() +
                '}';
    }

    public BaseGroupDetails getGroupDetails() {
        return GroupDetails;
    }

    public void setGroupDetails(BaseGroupDetails groupDetails) {
        GroupDetails = groupDetails;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setHeadimageurl(String headimageurl) {
        this.headimageurl = headimageurl;
    }

    public String getHeadimageurl() {
        return this.headimageurl;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return this.sex;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }
}
