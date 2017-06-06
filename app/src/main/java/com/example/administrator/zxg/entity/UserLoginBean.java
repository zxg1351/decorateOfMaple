package com.example.administrator.zxg.entity;

import java.io.Serializable;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/6/6 11:51
 */

public class UserLoginBean extends BaseInfo  {

    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
