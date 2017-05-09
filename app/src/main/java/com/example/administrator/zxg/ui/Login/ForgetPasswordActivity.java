package com.example.administrator.zxg.ui.Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.util.ToastUtil;

/**
 * Created by Administrator on 2016/10/5.
 */
public class ForgetPasswordActivity extends CommonActivity {
    private TextView tv_title;
    private EditText et_newPassword,et_mobile,et_verificationCode;
    private String etMobile,etVerificationCode,etNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tv_title =(TextView) findViewById(R.id.tv_title);//标题
        tv_title.setText(R.string.changePassword);
        et_mobile = (EditText) findViewById(R.id.et_mobile);//手机号码
        et_verificationCode =(EditText) findViewById(R.id.et_verificationCode);//验证码
        et_newPassword = (EditText) findViewById(R.id.et_newPassword);//新密码
    }

    /**
     * 获取详细内容
     */
    private void getContent(){

        etMobile = et_mobile.getText().toString().trim();
        etVerificationCode = et_verificationCode.getText().toString().trim();
        etNewPassword   = et_newPassword.getText().toString().trim();

    }

    /**
     * 修改密码提交功能
     * @param view
     */
    public void submit(View view){

        getContent();
        if (TextUtils.isEmpty(etMobile)){
            ToastUtil.showShort(ForgetPasswordActivity.this,getString(R.string.input_mobile));
        }else if (TextUtils.isEmpty(etVerificationCode)){
            ToastUtil.showShort(ForgetPasswordActivity.this,getString(R.string.verificationCode));
        }else if (TextUtils.isEmpty(etNewPassword)){
            ToastUtil.showShort(ForgetPasswordActivity.this,getString(R.string.input_newPassword));
        }else {

            ToastUtil.showShort(ForgetPasswordActivity.this,getString(R.string.commit_success));
            finish();
        }
    }

    /***
     * 获取验证码
     * @param view
     */
    public void sendVerificationCode(View view){




    }
}
