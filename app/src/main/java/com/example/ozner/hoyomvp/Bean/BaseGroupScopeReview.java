package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by ozner_67 on 2016/3/25.
 * 审核进度
 */
public class BaseGroupScopeReview implements Serializable {
    private int id;
    //组编号
    private int GroupNumber;
    //权限
    private String Scope;
    //备注
    private String Remark;
    //审核进度
    private int ReviewState;
    //审核备注
    private String ReviewRemark;
    //审核人
    private int ReviewBy;
    //审核时间
    private String ReviewTime;
    //创建时间
    private String CreateTime;

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

    public void setReviewState(int reviewState) {
        this.ReviewState = reviewState;
    }

    public int getReviewState() {
        return this.ReviewState;
    }

    public void setReviewRemark(String reviewRemark) {
        this.ReviewRemark = reviewRemark;
    }

    public String getReviewRemark() {
        return this.ReviewRemark;
    }

    public void setReviewBy(int reviewBy) {
        this.ReviewBy = reviewBy;
    }

    public int getReviewBy() {
        return this.ReviewBy;
    }

    public void setReviewTime(String reviewTime) {
        this.ReviewTime = reviewTime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.ReviewTime = Long.parseLong(reviewTime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.ReviewTime = 0;
//        }
    }

    public long getReviewTime() {
        try {
            return Long.parseLong(ReviewTime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
//        return this.ReviewTime;
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
}
