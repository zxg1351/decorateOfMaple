package com.example.administrator.zxg.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.adapter.GuidePageAdapter;
import com.example.administrator.zxg.common.CommonActivity;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/3 17:02
 */

public class MainActivity2 extends CommonActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewpagerTest;
    private LinearLayout viewGroup;
    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    //图片资源ID
    private int[] imgIdArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        initView();

    }

    private void initView() {
        viewpagerTest =(ViewPager)  findViewById(R.id.viewpager);
        viewGroup =(LinearLayout) findViewById(R.id.viewGroup);


        imgIdArray = new int[]{R.mipmap.crab,R.mipmap.hugh};
        //将点点加入到viewgroup中
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));
            tips[i] = imageView;
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            viewGroup.addView(imageView, layoutParams);


        }
        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewpagerTest.setAdapter(new GuidePageAdapter(mImageViews));
        //设置监听，主要是这是点点的背景
        viewpagerTest.setOnPageChangeListener(this);
        viewpagerTest.setCurrentItem((mImageViews.length)*100);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
