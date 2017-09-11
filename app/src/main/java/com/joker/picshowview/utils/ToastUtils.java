package com.joker.picshowview.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by aa on 2017/9/4.
 */

public class ToastUtils {

    private static Toast mToast;
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
}
