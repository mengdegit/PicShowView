package com.joker.picshowview.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * ScreenUtils Created by hanj on 14-9-25.
 */
public class ScreenUtils {
	private static int screenW;
	private static int screenH;

	public static int getScreenW(Activity mActivity) {
		if (screenW == 0) {
			initScreen(mActivity);
		}
		return screenW;
	}

	public static int getScreenH(Activity mActivity) {
		if (screenH == 0) {
			initScreen(mActivity);
		}
		return screenH;
	}

	private static void initScreen(Activity mActivity) {
		DisplayMetrics metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenW = metric.widthPixels;
		screenH = metric.heightPixels;
	}
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }
	public static int getColorWithAlpha(float alpha, int baseColor) {
		int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
		int rgb = 0x00ffffff & baseColor;
		return a + rgb;
	}
}
