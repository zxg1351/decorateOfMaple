package com.example.administrator.zxg.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    private LinearLayout ll_repage_back,ll_repage_next,ll_background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginxxx);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.title_login);
        et_userName = (EditText) findViewById(R.id.et_userName);
        et_password = (EditText) findViewById(R.id.et_password);
        ll_repage_back = (LinearLayout) findViewById(R.id.repage_back);
        ll_repage_next = (LinearLayout) findViewById(R.id.repage_next);
        ll_background =(LinearLayout)findViewById(R.id.ll_background);
        ll_repage_back.setClickable(false);
        ll_repage_next.setClickable(false);
        ll_repage_next.setVisibility(View.INVISIBLE);
        ll_repage_back.setVisibility(View.INVISIBLE);
//        ll_background.getBackground().setAlpha(70);

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
        } else if (etUserName.length() < 6||etUserName.length()>16) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userName_length));
        } else if (TextUtils.isEmpty(etPassWord)) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userPassword));
        } else if (etPassWord.length() < 6||etPassWord.length()>16) {
            ToastUtil.showShort(LoginActivity.this, getString(R.string.input_userPassword_length));
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    /**
     * 注册
     * @param view
     */
    public void registered(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 忘记密码
     * @param view
     */
    public void  forgetPassword(View view){

        Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(intent);
    }
}