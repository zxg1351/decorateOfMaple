package com.example.administrator.zxg.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;



/**
 * Created by Administrator on 2016/9/25.
 */
public class WelcomeActivity extends CommonActivity {



    private ImageView iv_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    public void init() {
        iv_image = (ImageView) findViewById(R.id.iv_image);
     //   String url = "http://www.qq745.com/uploads/allimg/141106/1-141106153Q5.png";
//        String url = "http://img2.3lian.com/2014/f6/173/d/51.jpg";
        Glide.with(this).
                load(R.mipmap.bg_start).
//                asBitmap(). //强制处理为bitmap
        into(iv_image);//显示到目标View中
    }

    public void onWelcome(View view) {

        Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
        startActivity(intent);
    }
}
