package com.joker.picshowview.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.picshowview.R;

import java.lang.reflect.Field;

/**
 * Created by aa on 2017/9/4.
 */

public class ToastUtils {

    private static Toast mToast;
    private static Toast mToastView;

    public static void showToast(Context context, int resId, int duration){
        showToast(context, context.getString(resId), duration);
    }
    public static void showToast(Context context, String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
    //关闭toast的方法
    public static void cancel(){
        if (mToast!=null){
            mToast.cancel();
        }
    }

    /**
     * 显示
     */
    public static void ToastShow(Context context, int drawable, String str) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_cancel, null);
        TextView text = (TextView) view.findViewById(R.id.textToast);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgToast);
        text.setText(str); // 设置显示文字
        imageView.setImageResource(drawable);
        if (mToastView ==null){
            mToastView = new Toast(context);
        }
        mToastView.setGravity(Gravity.CENTER, 0, 0); // Toast显示的位置
        mToastView.setDuration(Toast.LENGTH_SHORT); // Toast显示的时间
        mToastView.setView(view);
        mToastView.show();
    }

    public static void show(Activity context, String message){
        Toast toastStart = new Toast(context);
        //加载toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_item, null);
        //初始化空间布局
        TextView mTextView = (TextView) toastRoot.findViewById(R.id.message);
        ImageView mImageView = (ImageView) toastRoot.findViewById(R.id.imageView);
        //为控件设置属性
        mTextView.setText(message);
        mImageView.setImageResource(R.mipmap.ic_launcher);
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, 0);
        toastStart.setDuration(Toast.LENGTH_LONG);
        toastStart.setView(toastRoot);
        //加入动画
        try {
            Object mTN ;
            mTN = getField(toastStart, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null
                        && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    params.windowAnimations = R.style.Lite_Animation_Toast;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置宽度
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        LinearLayout layout2 = (LinearLayout)toastRoot.findViewById(R.id.toast_layout);
        layout2.getLayoutParams().width = screenWidth;
        toastStart.show();
    }


    /**
     * 反射字段
     * @param object 要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }
}
