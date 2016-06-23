package com.myfstrong.mycniao5.bean;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class Category extends BaseBean {
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(long id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
