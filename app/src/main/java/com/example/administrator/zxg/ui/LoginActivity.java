package com.example.administrator.zxg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zxg.MainActivity;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.util.ToastUtil;

/**
 * Created by Administrator on 2016/10/5.
 */
public class LoginActivity extends CommonActivity {
    private TextView tv_title;
    private EditText et_userName, et_password;
    private String etUserName, etPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.title_login);
        et_userName = (EditText) findViewById(R.id.et_userName);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    /**
     * 登录按钮
     *
     * @param view
     */
    public void login(View view) {

         etUserName = et_userName.getText().toString().trim();
         etPassWord = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(etUserName)) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userName));
        } else if (etUserName.length() < 6) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userName_length));
        } else if (TextUtils.isEmpty(etPassWord)) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userPassword));
        } else if (etPassWord.length() < 6) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userPassword_length));
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    /**
     * 忘记密码
     * @param view
     */
    public void forgetPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
