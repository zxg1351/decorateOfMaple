package com.example.administrator.zxg.ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.util.ToastUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/10/22.
 */
public class UserProtocolActivity extends CommonActivity implements View.OnClickListener {
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("用户注册协议");
        Logger.d("用户注册协议：");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.agree:
                ToastUtil.showShort(this,"同意用户注册协议！！！");
            case R.id.disagree:
                ToastUtil.showShort(this,"不同意用户注册协议！！！");
        }
    }

}
