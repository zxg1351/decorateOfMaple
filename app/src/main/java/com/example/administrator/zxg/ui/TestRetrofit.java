package com.example.administrator.zxg.ui;

import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/6/2 17:03
 */

public class TestRetrofit extends CommonActivity {

    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testretrofit);
        initView();
    }
    //初始化界面
    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("testretrofit");
    }
}
