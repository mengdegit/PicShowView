package com.joker.picshowview.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by aa on 2017/7/26.
 */

public class CircleView extends View{
    private Context mContext;
    private Paint paint;
    private int mWidth;
    private int mHeight;
    private float posX;
    private float posY;
    private boolean isMove = false;
    private final int minRadius = 50;
    private final int maxRadius = 200;

    public CircleView(Context context) {
        super(context);

    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.mContext =context;
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        posX = mWidth / 2;
        posY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, maxRadius, paint);
        canvas.drawCircle(posX, posY, minRadius, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;

    }
}
