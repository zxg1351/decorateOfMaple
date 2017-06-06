package com.example.administrator.zxg.entity;

import java.io.Serializable;


/**
 * @Description
 * @Author bayonet1351
 * Created 2017/6/6 10:23
 */
public class DemoModel implements Serializable {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
