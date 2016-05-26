package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by ozner_67 on 2016/3/25.
 * 组成员信息
 */
public class BaseGroupMember implements Serializable {
    private int id;
    private int GroupNumber;
    private int UserId;
    private CurrentUser user;
    private String Scope;
    private String Remark;
    private int State;
    private String JoinTime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setGroupNumber(int groupNumber) {
        this.GroupNumber = groupNumber;
    }

    public int getGroupNumber() {
        return this.GroupNumber;
    }

    public void setUser(CurrentUser user) {
        this.user = user;
    }

    public CurrentUser getUser() {
        return this.user;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public int getUserId() {
        return this.UserId;
    }

    public void setScope(String scope) {
        this.Scope = scope;
    }

    public String getScope() {
        return this.Scope;
    }

    public void setRemark(String remark) {
        this.Remark = remark;
    }

    public String getRemark() {
        return this.Remark;
    }

    public void setState(int state) {
        this.State = state;
    }

    public int getState() {
        return this.State;
    }

    public void setJoinTime(String joinTime) {
        this.JoinTime = joinTime.replace("/Date(", "").replace(")/", "");

//        try {
//            this.JoinTime = Long.parseLong(joinTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.JoinTime = 0;
//        }
    }

    public long getJoinTime() {
//        return this.JoinTime;
        try {
            return Long.parseLong(JoinTime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
