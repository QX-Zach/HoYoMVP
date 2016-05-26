package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by xinde on 2016/3/15.
 */
public class RealNameDetail implements Serializable {
    //姓名
    private String name;
    //身份证(中间部分掩码***)
    @Override
    public String toString() {
        return "RealNameDetail{" +
                "name='" + name + '\'' +
                ", cardid='" + cardid + '\'' +
                ", checkstate=" + checkstate +
                '}';
    }private String cardid;
    //审核状态：0-请上传身份证审核，1-正在进行审核，2-审核通过，3-审核驳回，可以重新提交材料审核
    private int checkstate;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCardid(String cardId) {
        this.cardid = cardId;
    }

    public String getCardid() {
        return this.cardid;
    }

    public void setCheckstate(int checkState) {
        this.checkstate = checkState;
    }

    public int getCheckstate() {
        return this.checkstate;
    }
}
