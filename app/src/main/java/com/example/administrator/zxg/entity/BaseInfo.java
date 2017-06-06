package com.example.administrator.zxg.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/6.
 */
public class BaseInfo implements Serializable {

    private String resultCode;
    private String resultMessage;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
