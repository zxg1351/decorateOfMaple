package com.example.administrator.zxg.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Administrator on 2017/6/12.
 */

public class UserBean extends BaseInfo {


   private String  mUserName;
    private String wxOpenId;
    private String  wbOpenId;
    private String qqOpenId;
    private String mUserTel;
    private String  mUserAccount;
    private String  mUserPassword;
    private Date createTime;
    private Integer createUser;
    private Date  updateTime;
    private Integer  updateUser;
    private String  delFlag;


    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWbOpenId() {
        return wbOpenId;
    }

    public void setWbOpenId(String wbOpenId) {
        this.wbOpenId = wbOpenId;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getmUserTel() {
        return mUserTel;
    }

    public void setmUserTel(String mUserTel) {
        this.mUserTel = mUserTel;
    }

    public String getmUserAccount() {
        return mUserAccount;
    }

    public void setmUserAccount(String mUserAccount) {
        this.mUserAccount = mUserAccount;
    }

    public String getmUserPassword() {
        return mUserPassword;
    }

    public void setmUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
