package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xinde on 2016/3/15.
 * 用户积分信息基础类
 */
public class BaseUserScore implements Serializable {
    //key
    private int id;
    //用户ID
    private int userId;

    @Override
    public String toString() {
        return "BaseUserScore{" +
                "id=" + id +
                ", userId=" + userId +
                ", score=" + score +
                ", number=" + number +
                ", way='" + way + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }

    //综合评分
    private int score;
    //增加的评价次数
    private int number;
    //方式 内部标识
    private String way;
    //修改时间
    private String modifyTime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getWay() {
        return this.way;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.modifyTime = Long.parseLong(modifyTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            this.modifyTime = 0;
//        }
    }

    public String getModifyTime() {
        return this.modifyTime;
    }
}
