package com.example.administrator.zxg.imageandvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;

import java.util.ArrayList;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/8 14:54
 */

public class SelectImageAndVideoActivity extends CommonActivity{
    private int code = 100;// 当前页定义码，用于取回数据后的使用
    private TextView tv_title;
    private ArrayList<String> dataList = new ArrayList<String>();// 存储图片路径
    private GridView  gv_pic_over;//现场照片和复查照片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);
        initView();
    }

    private void initView() {

        tv_title =(TextView) findViewById(R.id.tv_title);
        tv_title.setText("图片拍摄选择");
        gv_pic_over = (GridView) findViewById(R.id.gv_pic_over);
    }

    public void selectImage(View view){
        Intent intent = new Intent(SelectImageAndVideoActivity.this,SelectPictrueActivity.class);
        intent.putExtra("touxiang",true);
        startActivityForResult(intent,code);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {// 处理取回的图片
        if (resultCode == RESULT_OK) {
            if (requestCode == code) {
                ArrayList<String> pathList = data
                        .getStringArrayListExtra("list");
                if (pathList.size() == 0) {
                    return;
                }
                for (String string : pathList) {
                    dataList.clear();
                    dataList.add(string);
                }
                gv_pic_over.setAdapter(new EventListAdapter(this, dataList));
//                currentId = dataList.size();
//                InitImage();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
