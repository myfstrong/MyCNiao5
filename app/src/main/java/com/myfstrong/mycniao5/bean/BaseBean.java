package com.myfstrong.mycniao5.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class BaseBean implements Serializable {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
