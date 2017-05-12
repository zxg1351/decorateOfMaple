package com.example.administrator.zxg.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.util.GlideImageUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/9/26.
 */
public class GlideActivity extends CommonActivity {

    private ImageView imageViewUrl,imageResource,imageLocal;
    String url="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        url    = "http://www.qq745.com/uploads/allimg/141106/1-141106153Q5.png";
        //初始化
        initView();
        //加载网络图片
        getInternetPicture(url);
        //加载资源文件
        getResourcesImage();
        //加载本地文件图片
        getLocalImageView();
    }

    private void getLocalImageView() {

        File file = new File(Environment.getExternalStorageDirectory(), "longzhu_icon.png");
        GlideImageUtils.loadCircleImageFile(this,file,imageLocal);
//        Glide.with(this).
//                load(file)
//                .asBitmap()
//                .placeholder(R.mipmap.icon_error)
//                .error(R.mipmap.icon_error)
//                .into(imageLocal);
    }
    /**
     * 初始化页面
     */
    private void initView() {
        imageViewUrl =(ImageView) findViewById(R.id.iv_imageUrl);
        imageResource =(ImageView) findViewById(R.id.iv_imageResource);
        imageLocal =(ImageView)findViewById(R.id.iv_imageLocal);
    }

    /**
     * 获取网络图片
     */
//    磁盘缓存,磁盘缓存通过diskCacheStrategy(DiskCacheStrategy)来设置,DiskCacheStrategy一共有4种模式:
//
//    DiskCacheStrategy.NONE:什么都不缓存
//    DiskCacheStrategy.SOURCE:仅缓存原图(全分辨率的图片)
//    DiskCacheStrategy.RESULT:仅缓存最终的图片,即修改了尺寸或者转换后的图片
//    DiskCacheStrategy.ALL:缓存所有版本的图片,默认模式
    private void getInternetPicture(String url) {

        GlideImageUtils.loadRoundCornerImage(this,url,imageViewUrl);
//        Glide.with(this)
//                .load(url)//url路径
//                .asBitmap()//强制处理为bitmiap
//                .placeholder(R.mipmap.icon_error)//加载中显示的图片
//                .error(R.mipmap.icon_error)//加载失败时显示的图片
//                .override(100,100)
//                .centerCrop()//中心裁剪,缩放填充至整个ImageView
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .thumbnail(0.1f)
//                .into(imageViewUrl);
    }

    /**
     * 获取资源图片
     */
    private void getResourcesImage() {

        GlideImageUtils.loadImageViewDrawable(this,R.mipmap.start,imageResource);
//        Glide.with(this)
//                .load(R.mipmap.vintage)
//                .asBitmap()
//                .placeholder(R.mipmap.icon_error)
//                .error(R.mipmap.icon_error)
//                .centerCrop()
//                .into(imageResource);

    }
}
