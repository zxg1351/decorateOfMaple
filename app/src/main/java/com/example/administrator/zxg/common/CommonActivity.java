package com.example.administrator.zxg.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/10/5.
 */
public class CommonActivity  extends Activity {

    public static final int REQUEST_CODE = 0;//启动Activity时的请求code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  void  onBackPressed(View view){

        finish();

    }
}
