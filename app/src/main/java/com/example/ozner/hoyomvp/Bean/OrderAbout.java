package com.example.ozner.hoyomvp.Bean;

import java.io.Serializable;

/**
 * Created by xinde on 2016/3/15.
 */
public class OrderAbout implements Serializable {
    //已完成数量
    private int finsh;

    @Override
    public String toString() {
        return "OrderAbout{" +
                "finsh=" + finsh +
                ", wait=" + wait +
                '}';
    }

    //待处理数量
    private int wait;

    public void setFinsh(int finsh) {
        this.finsh = finsh;
    }

    public int getFinsh() {
        return this.finsh;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getWait() {
        return this.wait;
    }
}
