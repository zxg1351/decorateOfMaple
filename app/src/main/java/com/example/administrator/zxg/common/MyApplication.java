package com.example.administrator.zxg.common;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/11 10:18
 */

public class MyApplication extends Application {
    private static String  TAG ="LoggerTest";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG)
                .logLevel(LogLevel.NONE)//设置不打印日志
        ;
    }

}
