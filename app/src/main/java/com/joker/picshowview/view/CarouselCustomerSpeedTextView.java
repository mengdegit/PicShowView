package com.joker.picshowview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.joker.picshowview.utils.TextUtils;

/**
 * Created by aa on 2018/3/26.
 */

public class CarouselCustomerSpeedTextView extends TextView implements Runnable{
    private static final String TAG = "CarouselTextView";
    private int currentScrollX = 0; // 当前滚动位置  X轴
    private int firstScrollX = 0;  //  初始位置
    private boolean isStop = false;  // 开始停止的标记
    private int textWidth;  // 文本宽度
    private int mWidth = 0; // 控件宽度
    private int speed = 2;  // 默认是两个点
    private int delayed = 1000; // 默认是1秒
    private int endX; // 滚动到哪个位置
    private boolean isFirstDraw=true; // 当首次或文本改变时重置
    private static final int SCROLL_DELAYED = 4 * 1000;

    public CarouselCustomerSpeedTextView(Context context) {
        super(context);
    }

    public CarouselCustomerSpeedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CarouselCustomerSpeedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarouselCustomerSpeedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void run() {
        Log.d(TAG, "run");
        currentScrollX += speed;  // 滚动速度每次加几个点
        scrollTo(currentScrollX, 0); // 滚动到指定位置
        if(isStop){
            return;
        }
        if(currentScrollX >= endX){   // 如果滚动的位置大于最大限度则滚动到初始位置
            scrollTo(firstScrollX, 0);
            currentScrollX = firstScrollX; // 初始化滚动速度
            postDelayed(this, SCROLL_DELAYED);  // SCROLL_DELAYED毫秒之后重新滚动
        }else {
            postDelayed(this, delayed);  // delayed毫秒之后再滚动到指定位置
        }
    }

    /**
     * @param
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    /**
     * @param
     */
    public void setDelayed(int delayed) {
        this.delayed = delayed;
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        isStop = false;
        this.removeCallbacks(this);  // 清空队列
        postDelayed(this, SCROLL_DELAYED);  // 4秒之后滚动到指定位置
    }

    /**
     * 停止滑动
     */
    public void stopScroll() {
        isStop = true;
    }

    /**
     * 从头开始滑动
     */
    public void startFor(){
        currentScrollX = 0;  // 将当前位置置为0
        startScroll();
    }
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        Log.d(TAG , "onTextChanged");
        isStop = true; // 停止滚动
        this.removeCallbacks(this);   // 清空队列
        currentScrollX = firstScrollX;  // 滚动到初始位置
        this.scrollTo(currentScrollX, 0);
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        // 需要重新设置参数
        isFirstDraw = true;
        isStop = false;
        postDelayed(this, SCROLL_DELAYED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG,MeasureSpec.getSize(widthMeasureSpec)+"++"+MeasureSpec.getSize(heightMeasureSpec)+"");
        ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isFirstDraw) {
            Log.d(TAG , "isFirstDraw=="+isFirstDraw);
            getTextWidth();
            firstScrollX = getScrollX(); // 获取第一次滑动的X轴距离
            System.out.println("firstScrollX======"+firstScrollX);
            currentScrollX = firstScrollX;
            mWidth = this.getWidth();  // 获取文本宽度，如果文本宽度大于屏幕宽度，则为屏幕宽度，否则为文本宽度
            Log.d(TAG , "mWidth======"+mWidth);
            endX = firstScrollX + textWidth - mWidth/2;  // 滚动的最大距离，可根据需要来定
            Log.d(TAG , "endX========"+endX);
            isFirstDraw = false;
        }
    }

    /**
     * 获取文字宽度
     */
    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
        mWidth = getWidth();
        currentScrollX=0;
    }

}
