package com.example.administrator.zxg.common;

import java.io.Serializable;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/3 13:20
 */

public class CommonBean implements Serializable {

    public String statusCode;//返回状态码

    public String statusMessage;//返回状态消息

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
