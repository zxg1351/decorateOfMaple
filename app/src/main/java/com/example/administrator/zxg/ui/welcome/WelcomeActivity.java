package com.example.administrator.zxg.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Administrator on] 2016/9/25.
 */
public class WelcomeActivity extends CommonActivity {


    private String JSON_CONTENT = "{\"weatherinfo\":{\"city\":\"北京\",\"cityid\":\"101010100\"," +
            "\"temp\":\"18\",\"WD\":\"东南风\",\"WS\":\"1级\",\"SD\":\"17%\",\"WSE\":\"1\"," +
            "\"time\":\"17:05\",\"isRadar\":\"1\",\"Radar\":\"JC_RADAR_AZ9010_JB\"," +
            "\"njd\":\"暂无实况\",\"qy\":\"1011\",\"rain\":\"0\"}}";
    private ImageView iv_image;
    private Button sp_jump_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Logger.d("执行了 onCreate");

        init();
        startClock();
        Logger.d("开始启动Clock任务");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    public void init() {
        sp_jump_btn = (Button)findViewById(R.id.sp_jump_btn);
        iv_image = (ImageView) findViewById(R.id.iv_image);
     //   String url = "http://www.qq745.com/uploads/allimg/141106/1-141106153Q5.png";
//        String url = "http://img2.3lian.com/2014/f6/173/d/51.jpg";
        Glide.with(this).
                load(R.mipmap.bg_start).
//                asBitmap(). //强制处理为bitmap
        into(iv_image);//显示到目标View中
    }
    //由于CountDownTimer有一定的延迟，所以这里设置3400
    private  CountDownTimer  countDownTimer = new CountDownTimer(3400, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            sp_jump_btn.setText("跳过(" + millisUntilFinished/1000 + "s)");
        }

        @Override
        public void onFinish() {
            sp_jump_btn.setText("跳过(" + 0 + "s)");
            Logger.d("倒计时三秒结束,进入到跳转开始页面");
            gotoStartActivity();

        }


    };

    private void gotoStartActivity() {

        countDownTimer.cancel();

//        if (UserCenter.getInstance().getToken() == null) {
        gotoNext();

////        } else {
//            gotoMainActivity();
//        }

    }

    private void gotoNext(){
        Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
        startActivity(intent);
        finish();

    }

    private void startClock() {
        sp_jump_btn.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer!=null){

            countDownTimer.cancel();
        }
    }


    public void spJest(View view){

        gotoStartActivity();
        Logger.d("手动跳转结束");

    }
    public void onWelcome(View view) {

        Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
        startActivity(intent);

        Logger.i("欢迎来到天道酬勤欢迎页");
        Logger.d("abcde");
        //json,当然还可以选择显示XML
        Logger.json(JSON_CONTENT);
        // List 类型数据
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        Logger.d(list);
        //map注释
        Map<String,String> map = new HashMap<>();
        map.put("key_hello","hello");
        map.put("key_world","world");
        Logger.d(map);

        //set注释
        Set<String> set = new HashSet<>();
        set.add("广州");
        set.add("深圳");
        set.add("北京");
        set.add("上海");
        Logger.d(set);

        //字符串注释
        Logger.d("hello %s %d", "world", 5);
//        Logger.e();
//        Logger.e();
//        Logger.init()
    }
}
