package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ozner_67 on 2016/3/25.
 * 团队详情实体模型
 */
public class AuthorityDetail implements Serializable {
    //组权限信息
    private BaseGroup scope;
    //团队创建人信息
    private CurrentUser user;
    //用户个人信息；null：创建人自己，其他：团队成员
    private CurrentUser userself;
    //审核情况
    private BaseGroupScopeReview review;
    //组资料
    private BaseGroupInfo groupinfo;
    //成员信息
    private List<BaseGroupMember> members;
    //权限属性和属性值
    private List<BaseScopeAttribute> attibutes;

    public CurrentUser getUserself() {
        return userself;
    }

    public void setUserself(CurrentUser userself) {
        this.userself = userself;
    }

    public void setScope(BaseGroup scope) {
        this.scope = scope;
    }

    public BaseGroup getScope() {
        return this.scope;
    }

    public void setUser(CurrentUser user) {
        this.user = user;
    }

    public CurrentUser getUser() {
        return this.user;
    }

    public void setReview(BaseGroupScopeReview review) {
        this.review = review;
    }

    public BaseGroupScopeReview getReview() {
        return this.review;
    }

    public void setGroupinfo(BaseGroupInfo groupinfo) {
        this.groupinfo = groupinfo;
    }

    public BaseGroupInfo getGroupinfo() {
        return this.groupinfo;
    }

    public void setMembers(List<BaseGroupMember> members) {
        this.members = members;
    }

    public List<BaseGroupMember> getMembers() {
        return this.members;
    }

    public void setAttibutes(List<BaseScopeAttribute> attibutes) {
        this.attibutes = attibutes;
    }

    public List<BaseScopeAttribute> getAttibutes() {
        return this.attibutes;
    }
}
