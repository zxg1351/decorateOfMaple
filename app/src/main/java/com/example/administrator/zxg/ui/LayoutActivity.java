package com.example.administrator.zxg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.entity.JsonBean;
import com.example.administrator.zxg.imageandvideo.SelectImageAndVideoActivity;
import com.example.administrator.zxg.ui.Glide.GlideActivity;
import com.example.administrator.zxg.ui.Picker.JsonDataActivity;
import com.example.administrator.zxg.ui.RecycleView.GridLayoutActivity;
import com.example.administrator.zxg.ui.RecycleView.MoreContentActivity;
import com.example.administrator.zxg.ui.RecycleView.StaggeredActivity;
import com.example.administrator.zxg.ui.RecycleView.StaggeredGridLayoutActivity;

import java.util.ArrayList;

/**
 * Created by Administratlor on 2016/8/30.
 */
public class LayoutActivity extends CommonActivity {
    public static final int MSG_LOAD_DATA = 0x0001;
    public static final int MSG_LOAD_SUCCESS = 0x0002;
    public static final int MSG_LOAD_FAILED = 0x0003;
    public Thread thread;
    public ArrayList<JsonBean> options1Items = new ArrayList<>();
    public ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    public boolean isLoaded = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    public void upLoadImage(View view) {

        Intent intent = new Intent(LayoutActivity.this, SelectPicActivity.class);
        startActivity(intent);

    }

    /**
     * 点击查看图片
     *
     * @param view
     */
    public void clickimage(View view) {
        Intent intent = new Intent(LayoutActivity.this, ImageActivity.class);
        startActivity(intent);
    }
    public void selectImageAndVideo(View view){
        Intent intent = new Intent(LayoutActivity.this, SelectImageAndVideoActivity.class);
        startActivity(intent);

    }
    //recycle
    public void recycle(View view) {
        Intent intent = new Intent(LayoutActivity.this, StaggeredGridLayoutActivity.class);
        startActivity(intent);
    }

    //gridviewlayout
    public void gridlayout(View view) {
        Intent intent = new Intent(LayoutActivity.this, GridLayoutActivity.class);
        startActivity(intent);

    }

    public void staggeral(View view) {
        Intent intent = new Intent(LayoutActivity.this, StaggeredActivity.class);
        startActivity(intent);

    }

    public void glideimage(View view) {
        Intent intent = new Intent(LayoutActivity.this, GlideActivity.class);
        startActivity(intent);

    }

    public void morecontent(View view) {
        Intent intent = new Intent(LayoutActivity.this, MoreContentActivity.class);
        startActivity(intent);

    }
    public void showPicker(final View view){
        Intent intent = new Intent(this, JsonDataActivity.class);
        startActivity(intent);
    }


}
