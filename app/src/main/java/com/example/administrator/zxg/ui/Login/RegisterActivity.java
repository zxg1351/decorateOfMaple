package com.example.administrator.zxg.ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.entity.UserBean;
import com.example.administrator.zxg.http.HttpMethods;
import com.example.administrator.zxg.util.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * @Description 注册功能
 * @Author bayonet1351
 * Created 2017/5/9 14:33
 */

public class RegisterActivity extends CommonActivity {
    private TextView tv_title;
    EditText et_password,et_user,et_telphone;

    private String userName,userTelphone,userPassword;
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
        userName = et_user.getText().toString().trim();
        userTelphone = et_telphone.getText().toString().trim();
        userPassword = et_password.getText().toString().trim();

        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);//你的昵称
        map.put("userPassword",userPassword);//密码
        map.put("userTelphone",userTelphone);//手机号账号

        HttpMethods.getInstance().registerUser(new Subscriber<UserBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserBean userBean) {
                if (checkNullResultCode(userBean.getResultCode())){

                    if (checkResultCode(userBean.getResultCode())){

                        ToastUtil.showShort(RegisterActivity.this,userBean.getResultMessage());
                        finish();
                    }
                }


            }
        },map);


    }
    public void cancel(View view){
        ToastUtil.showShort(RegisterActivity.this,"关闭页面成功");
        finish();
        Logger.d("hello");
        Logger.i("activity created");
    }

    /**
     * 判断resultCode空值判断
     * @param resultCode
     * @return
     */
    private boolean checkNullResultCode(String resultCode){

        if (StringUtils.isEmpty(resultCode)){
            return false;
        }else {
            return true;
        }
    }
    private boolean checkResultCode(String resultCode){
        if (resultCode.equals("0")){
            return true;
        }else {
            return false;
        }
    }

}
