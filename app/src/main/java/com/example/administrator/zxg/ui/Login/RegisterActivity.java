package com.example.administrator.zxg.ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.util.ToastUtil;
import com.orhanobut.logger.Logger;

/**
 * @Description 注册功能
 * @Author bayonet1351
 * Created 2017/5/9 14:33
 */

public class RegisterActivity extends CommonActivity {
    private TextView tv_title;
    EditText et_password,et_user,et_telphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {


        et_user  = (EditText) findViewById(R.id.et_user);
        et_telphone = (EditText)findViewById(R.id.et_telphone);
        et_password =( EditText) findViewById(R.id.et_password);

    }
    public void registered(View view){
        ToastUtil.showShort(RegisterActivity.this,"注册成功");
    }
    public void cancel(View view){
        ToastUtil.showShort(RegisterActivity.this,"关闭页面成功");
        finish();
        Logger.d("hello");
        Logger.i("activity created");
    }
}
