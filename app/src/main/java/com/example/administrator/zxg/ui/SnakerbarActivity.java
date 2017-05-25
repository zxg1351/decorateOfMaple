package com.example.administrator.zxg.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/25 14:13
 */

public class SnakerbarActivity extends CommonActivity {
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_test);
        initView();

    }

    private void initView() {
       tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("snackbar点击事件");
    }


    public void clicksnackbar(View view){
        Snackbar s = Snackbar.make(view,"是否进行测试",Snackbar.LENGTH_SHORT);
        s.show();
    }
}
