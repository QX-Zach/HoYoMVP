package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by ozner_67 on 2016/3/25.
 * 组的权限属性信息
 */
public class BaseScopeAttribute implements Serializable {
    private int id;
    private int scopeid;
    private String scope;
    private String scopename;
    private String scopevalue;
    private String createtime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setScopeid(int scopeid) {
        this.scopeid = scopeid;
    }

    public int getScopeid() {
        return this.scopeid;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScopename(String scopename) {
        this.scopename = scopename;
    }

    public String getScopename() {
        return this.scopename;
    }

    public void setScopevalue(String scopevalue) {
        this.scopevalue = scopevalue;
    }

    public String getScopevalue() {
        return this.scopevalue;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime.replace("/Date(", "").replace(")/", "");
//        try {
//            this.createtime = Long.parseLong(createtime.replace("/Date(", "").replace(")/", ""));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            this.createtime = 0;
//        }
    }

    public long getCreatetime() {
//        return this.createtime;
        try {
            return Long.parseLong(createtime.replace("/Date(", "").replace(")/", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
