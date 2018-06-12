package com.joker.picshowview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeTextView extends TextView implements Runnable {
    private int currentScrollX;// 当前滚动的位置
    private boolean isStop = false;
    private int textWidth;
    private int width;
    private boolean isMeasure = false;
    private Context context;

    public MarqueeTextView(Context context) {
        super(context);
        this.context=context;
        // TODO Auto-generated constructor stub
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (!isMeasure) {// 文字宽度只需获取一次就可以了
            getTextWidth();
            isMeasure = true;
        }
    }

    /**
     * 获取文字宽度
     */
    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
        width = getWidth();
        currentScrollX=0;
    }

    @Override
    public void run() {
        currentScrollX += 5;// 滚动速度
        scrollTo(currentScrollX, 0);
        if (isStop) {
            return;
        }
        if (getScrollX()>= textWidth) {
            scrollTo(getResources().getDisplayMetrics().widthPixels, 0);
            currentScrollX =-getResources().getDisplayMetrics().widthPixels;
//                    return;
        }
        postDelayed(this, 10);
    }

    // 开始滚动
    public void startScroll() {
        isStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    // 停止滚动
    public void stopScroll() {
        isStop = true;
    }

    // 从头开始滚动
    public void startFor0() {
        currentScrollX = 0;
        startScroll();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        // TODO Auto-generated method stub
        super.setText(text, type);
        startScroll();
    }

    @Override
    public void destroyDrawingCache() {
        // TODO Auto-generated method stub
        super.destroyDrawingCache();

    }
}