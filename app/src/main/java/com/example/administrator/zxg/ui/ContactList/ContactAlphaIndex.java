package com.example.administrator.zxg.ui.ContactList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/20 11:14
 */

public class ContactAlphaIndex extends View {

    private OnTouchingLetterChangedListener listener;


    //定义了显示在最右边的浮动的索引项的列表,当然这个是固定的，所以可以直接初始化，如果需要变动的话则可以通过自定义属性来指定


    String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z","#"};

    int choose =-1;//用于标记点击存放字母数组中的下标
    Paint paint = new Paint();
    //这个变量主要是控制当我们点击索引列表中整个索引列表的背景有个变化,为false表示开始没点击背景为正常显示时候的背景
    boolean showBackground = false;


    public ContactAlphaIndex(Context context) {
        super(context);
    }

    public ContactAlphaIndex(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContactAlphaIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (showBackground){

            canvas.drawColor(Color.parseColor("#10000000"));
        }

        /**
         * 注意:在自定义view中的如果不需要设置wrap_content属性就不需要自己重写onMeasure方法
         * 因为在onMeasure方法中系统默认会自动测量两种模式:一个是match_parent另一个则是自己指定明确尺寸大小
         * 这两种方法对应着这一种MeasureSpec.AT_MOST测量模式，由于我们设置这个自定义浮动的字母索引表宽度是指定明确大小
         * 高度是match_parent模式，所以这里就不要手动测量了直接通过getHeight和getWidth直接拿到系统自动测量好高度和宽度
         * */

        int height = getHeight();
        int width = getWidth();
        ////让整个显示的每个字母均分整个屏幕高度尺寸，这个singleHeight就是每个字母占据的高度
        int signleHeight = height/b.length;

        for (int i = 0; i < b.length; i++) {

            //绘制字母text的颜色
            paint.setColor(Color.parseColor("#515151"));
            //绘制字母text的字体大小
            paint.setTextSize(25);
            //绘制字母text的字体样式
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            //设置抗锯齿样式
            paint.setAntiAlias(true);


            if (i==choose){


                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }

            float xPos = width/2 -paint.measureText(b[i]);
            float yPos =  signleHeight * i + signleHeight;//得到绘制字母text的起点的Y坐标

            canvas.drawText(b[i],xPos,yPos,paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {//重写view的触摸事件分发方法
        final int action = event.getAction();
        final float y = event.getY();//由于只涉及到Y轴坐标,只获取y坐标
        final int oldChoose = choose;//oldChoose用于记录上一次点击字母所在字母数组中的下标
        final int c = (int) (y / getHeight() * b.length);//得到点击或触摸的位置从而确定对应点击或触摸的字母所在字母数组中的下标
        switch (action) {
            case MotionEvent.ACTION_DOWN://监听按下事件
                showBackground = true;//按下后整个view的背景变色,showBkg为true
                if (oldChoose != c && listener != null) {//如果此次点击的字母数组下标不等于上一次的且已经注册了监听事件的,
                    if (c >= 0 && c <= b.length) {//并且点击得到数组下标在字母数组范围内的，我们就将此时的字母回调出去
                        listener.onTouchingLetterChanged(b[c]);//我们就将此时的对应在字母数组中的字母回调出去
                        choose = c;//并且更新当前选中的字母下标存储在choose变量中
                        invalidate();//最后通知canvas重新绘制
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE://监听移动事件,因为按下的时候已经把背景showBkg设置true,这里就不需要重新设置,其他操作与按下的事件一致
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c <= b.length) {
                        listener.onTouchingLetterChanged(b[c]);
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP://监听手指抬起的动作
                showBackground = false;//此时的背景将会恢复到初始状态,showBkg=false
                choose = -1;//此时记录下标的变量也需要重置
                invalidate();//并且重绘整个view
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    /**
     * 注册自定义监听器
     * */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener listener) {
        this.listener = listener;
    }
    /**
     * 定义一个接口,用于回调出点击后的字母,显示在弹出的字母对话框中
     * */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }
}
