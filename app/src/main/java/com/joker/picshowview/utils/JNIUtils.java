package com.joker.picshowview.utils;

/**
 * Created by aa on 2017/11/1.
 */

public class JNIUtils {
    static {
        System.loadLibrary("huazict");
    }

    //java调C中的方法都需要用native声明且方法名必须和c的方法名一样
    public native String getString();

}
