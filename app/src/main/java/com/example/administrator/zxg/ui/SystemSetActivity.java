package com.example.administrator.zxg.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.zxg.R;

/**
 * Created by Administrator on 2016/10/22.
 */
public class SystemSetActivity extends Activity {

    private TextView  tv_project_name;
    private TextView tv_test,tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        initView();
    }

    private void initView() {

        tv_project_name = (TextView) findViewById(R.id.tv_project_name);
        tv_test = (TextView) findViewById(R.id.tv_test);
        tv_title=(TextView) findViewById(R.id.tv_title);
        tv_title.setText("系统设置");
    }
}
