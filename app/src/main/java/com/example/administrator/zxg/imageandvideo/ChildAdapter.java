package com.example.administrator.zxg.imageandvideo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChildAdapter extends BaseAdapter {
    private Point mPoint = new Point(0, 0);// 用来封装ImageView的宽和高的对象
    /**
     * 用来存储图片的选中情况
     */
    private TextView textView;
    private CheckBox currentCB = null;
    public static int PHOTO_MARGIN = 50;
    private Map<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();// 该表用来存储选中的图片及其状态
    private GridView mGridView;
    private List<String> list;
    private List<Boolean> tureorfalse = new ArrayList<Boolean>();// 储存所有图片的选择状态
    protected LayoutInflater mInflater;
    private Context mContext;
    private Activity mActivity;
    private String urlString, urlImage;


    public ChildAdapter(Context context, List<String> list, GridView mGridView) {
        this.list = list;
        this.mGridView = mGridView;
        mContext = context;
        mActivity = (ShowImageActivity) context;
        mInflater = LayoutInflater.from(context);
        for (int i = 0; i < list.size(); i++) {

            tureorfalse.add(false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final String path = list.get(position);
        if (convertView == null) {
            convertView = ((Activity) mContext).getLayoutInflater().inflate(
                    R.layout.grid_child_item, parent, false);
            // convertView = mInflater.inflate(R.layout.grid_child_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (MyImageView) convertView
                    .findViewById(R.id.child_image);
            viewHolder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.child_checkbox);
            viewHolder.tv_showadapter = (TextView) convertView
                    .findViewById(R.id.tv_showadapter);
            // 用来监听ImageView的宽和高
            viewHolder.mImageView.setOnMeasureListener(new MyImageView.OnMeasureListener() {
                @Override
                public void onMeasureSize(int width, int height) {
                    mPoint.set(width, height);
                }
            });

            convertView.setTag(viewHolder);
            viewHolder.mCheckBox.setTag(position);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mImageView
                    .setImageResource(R.mipmap.friends_sends_pictures_no);
        }
        viewHolder.mImageView.setTag(path);
        textView = viewHolder.tv_showadapter;
        if (getSelectItems().size()
                + mActivity.getIntent().getIntExtra("count", 0) < 10) {
            viewHolder.mCheckBox.setChecked(tureorfalse.get(position));
        }
        // 对mCheckBox做点击监听，先赋值初始状态，然后通过点击来进行记录，由于checkBox在滑动过程中会被重置控件，所以废弃OnCheckedChangeListener的写法
        viewHolder.mCheckBox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // 首先判断取图片类型。分为9图和1图,现在添加20图模式
                int count = getSelectItems().size()
                        + mActivity.getIntent().getIntExtra("count", 0);// 计算当前选择的图片数量（包括之前选中的图片）
                if (mActivity.getIntent().getBooleanExtra("touxiang",// 如果选择为头像模式
                        false)) {
                    if (count > 0) {// 如果当前已经有一张图片
                        viewHolder.mCheckBox.setChecked(false);// 点击后强制为不选中效果
                        if (tureorfalse.get(position)) {// 如果点击的是一张被选中的图片
                            tureorfalse.set(position, false);// 将其状态改为未选中
                            mSelectMap.put(position, false);// 将点击过的图片状态储存
                            mSelectMap.remove(position);// 移出这张图片
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .add();
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .addToList(path, -1);// 调用Actvity中的方法，将此图片删除
                        } else {
                        ToastUtil.showShort(mContext,"您只能选1张图片");// 如果点击的是未被选中的图片，toast提示不能选择的原因，不做任何其他操作
                        }
                    } else {// 如果选择不满5张图片。
                        if (tureorfalse.get(position)) {// 如果所点击的图片为选中状态
                            tureorfalse.set(position, false);// 在总状态表中将其标记为未选中状态
                            mSelectMap.put(position, false);// 在选中表中将其标记为为选中状态
                            mSelectMap.remove(position);// 将该条记录删除（这句代码与上一句从逻辑上属于重复关系，但是现在有部分地方未能整理清除，暂且留下）
                            // 下面的两句代码的含义是取消选中，并且从储存图片地址表中将其清除
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .add();
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .addToList(path, -1);
                        } else {// 如果这张图片未被选择过
                            tureorfalse.set(position, true);// 在总表中将其状态改为已选择 。
                            mSelectMap.put(position, true);// 在以选中表中，将其状态定义为选中。
                            // 将该图片地址存入Activity的列表中
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .add();
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .addToList(path, 1);
                        }

                    }
                }else {
                    if (count >= 5) {// 如果当前已经有一张图片
                        viewHolder.mCheckBox.setChecked(false);// 点击后强制为不选中效果
                        if (tureorfalse.get(position)) {// 如果点击的是一张被选中的图片
                            tureorfalse.set(position, false);// 将其状态改为未选中
                            mSelectMap.put(position, false);// 将点击过的图片状态储存
                            mSelectMap.remove(position);// 移出这张图片
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .add();
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .addToList(path, -1);// 调用Actvity中的方法，将此图片删除
                        } else {
                            ToastUtil.showShort(mContext,"您只能选5张图片");// 如果点击的是未被选中的图片，toast提示不能选择的原因，不做任何其他操作
                        }
                    } else {// 如果选择不满5张图片。
                        if (tureorfalse.get(position)) {// 如果所点击的图片为选中状态
                            tureorfalse.set(position, false);// 在总状态表中将其标记为未选中状态
                            mSelectMap.put(position, false);// 在选中表中将其标记为为选中状态
                            mSelectMap.remove(position);// 将该条记录删除（这句代码与上一句从逻辑上属于重复关系，但是现在有部分地方未能整理清除，暂且留下）
                            // 下面的两句代码的含义是取消选中，并且从储存图片地址表中将其清除
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .add();
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .addToList(path, -1);
                        } else {// 如果这张图片未被选择过
                            tureorfalse.set(position, true);// 在总表中将其状态改为已选择 。
                            mSelectMap.put(position, true);// 在以选中表中，将其状态定义为选中。
                            // 将该图片地址存入Activity的列表中
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .add();
                            ((ShowImageActivity) mContext).getCountImageWsdl()
                                    .addToList(path, 1);
                        }

                    }
                }


            }
        });

        // 利用NativeImageLoader类加载本地图片
        Bitmap bitmap = NativeImageLoader.getInstance().loadNativeImage(path,
                mPoint, new NativeImageLoader.NativeImageCallBack() {
                    @Override
                    public void onImageLoader(Bitmap bitmap, String path) {
                        ImageView mImageView = (ImageView) mGridView
                                .findViewWithTag(path);
                        if (bitmap != null && mImageView != null) {
                            mImageView.setImageBitmap(bitmap);
                        }
                    }
                });
        if (bitmap != null) {
            viewHolder.mImageView.setImageBitmap(bitmap);
        } else {
            viewHolder.mImageView
                    .setImageResource(R.mipmap.friends_sends_pictures_no);
        }

        return convertView;
    }

    /**
     * 点击放大图片
     */
    private void scaleImage(int postition) {
        // 加载popupWindow的布局文件
        View contentView = mInflater.inflate(R.layout.popup, null);
        // 设置popupWindow的背景颜色
        contentView.setBackgroundColor(Color.BLACK);
        // 声明一个弹出框
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(textView, Gravity.CENTER_VERTICAL, 0, 0);
        ImageView imageView = (ImageView) contentView.findViewById(R.id.logo_b);
        int width = ScreenUtils.getScreenWidth(mContext) - PHOTO_MARGIN;
        int height = ScreenUtils.getScreenHeight(mContext) - PHOTO_MARGIN;
        Bitmap bitmap = BitmapFactory.decodeFile(list.get(postition));
        bitmap = BitmapUtil.getimage(list.get(postition));
        imageView.setMaxHeight(height);
        imageView.setMaxHeight(width);
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 给CheckBox加点击动画，利用开源库nineoldandroids设置动画
     *
     * @param view
     */
    private void addAnimation(View view) {
        float[] vaules = new float[]{0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f,
                1.1f, 1.2f, 1.3f, 1.25f, 1.2f, 1.15f, 1.1f, 1.0f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),
                ObjectAnimator.ofFloat(view, "scaleY", vaules));
        set.setDuration(150);
        set.start();
    }

    /**
     * 获取选中的Item的position
     *
     * @return
     */
    public List<Integer> getSelectItems() {
        List<Integer> list = new ArrayList<Integer>();
        for (Iterator<Map.Entry<Integer, Boolean>> it = mSelectMap.entrySet()
                .iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Boolean> entry = it.next();
            if (entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public class ViewHolder {
        public TextView tv_showadapter;
        public MyImageView mImageView;
        public CheckBox mCheckBox;
    }
}
