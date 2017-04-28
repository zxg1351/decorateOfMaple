package com.example.administrator.zxg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.uitl.CommonActivity;


/**
 * Created by Administrator on 2016/9/25.
 */
public class WelcomeActivity extends CommonActivity {

    private ImageView iv_image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iv_image =(ImageView) findViewById(R.id.iv_image);
//        Glide.with(this).load(R.mipmap.start).asBitmap().into(iv_image);

        String url = "http://www.qq745.com/uploads/allimg/141106/1-141106153Q5.png";
        Glide.with(this).
                load(url).
//                asBitmap(). //强制处理为bitmap
                into(iv_image);//显示到目标View中

    }

    public void onWelcome(View view) {

        Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
        startActivity(intent);
    }
}
