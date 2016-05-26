package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by ozner_67 on 2016/3/25.
 * 组基本信息
 */
public class BaseGroup implements Serializable {
    //组编号
    private int GroupNumber;
    //组权限
    private String GroupScope;
    //创建人ID
    private int UserId;
    //组状态
    private int GropState;
    //创建时间
    private String CreateTime;
    //修改人
    private int ModifyBy;
    //修改时间
    private String ModifyTime;

    public void setGroupNumber(int groupNumber) {
        this.GroupNumber = groupNumber;
    }

    public int getGroupNumber() {
        return this.GroupNumber;
    }

    public void setGroupScope(String groupScope) {
        this.GroupScope = groupScope;
    }

    public String getGroupScope() {
        return this.GroupScope;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public int getUserId() {
        return this.UserId;
    }

    public void setGropState(int gropState) {
        this.GropState = gropState;
    }

    public int getGropState() {
        return this.GropState;
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
            return 0;
        }
    }

    public void setModifyBy(int modifyBy) {
        this.ModifyBy = modifyBy;
    }

    public int getModifyBy() {
        return this.ModifyBy;
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

    public String getModifyTime() {
        return this.ModifyTime;
    }
}
