package com.example.administrator.zxg.ui.Login;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;

/**
 * @Description 注册功能
 * @Author bayonet1351
 * Created 2017/5/9 14:33
 */

public class RegisterActivity extends CommonActivity {
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        tv_title =  (TextView) findViewById(R.id.tv_title);
        tv_title.setText("注册");

    }

}
