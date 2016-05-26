package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinde on 2016/3/15.
 */
public class IndexInfo implements Serializable {
    private CurrentUser user;
    private BaseUserScore score;
    private RealNameDetail realname;
    private OrderAbout orderabout;
    private List<BaseImages> bannerimgs;

    @Override
    public String toString() {
        return "IndexInfo{" +
                "user=" + user +
                ", score=" + score +
                ", realname=" + realname +
                ", orderabout=" + orderabout +
                ", bannerimgs=" + bannerimgs +
                ", bdimgs=" + bdimgs +
                '}';
    }

    private List<BaseImages> bdimgs;

    public void setUser(CurrentUser user) {
        this.user = user;
    }

    public CurrentUser getUser() {
        return this.user;
    }

    public void setScore(BaseUserScore score) {
        this.score = score;
    }

    public BaseUserScore getScore() {
        return this.score;
    }

    public void setRealname(RealNameDetail realname) {
        this.realname = realname;
    }

    public RealNameDetail getRealname() {
        return this.realname;
    }

    public void setOrderabout(OrderAbout about) {
        this.orderabout = about;
    }

    public OrderAbout getOrderabout() {
        return this.orderabout;
    }

    public void setBannerimgs(List<BaseImages> bannerimgs) {
        this.bannerimgs = bannerimgs;
    }

    public List<BaseImages> getBannerimgs() {
        return this.bannerimgs;
    }

    public void setBdimgs(List<BaseImages> bdimgs) {
        this.bdimgs = bdimgs;
    }

    public List<BaseImages> getBdimgs() {
        return this.bdimgs;
    }
}
