package com.joker.picshowview.utils;

import android.os.Handler;
import android.util.Log;

/**
 * Created by aa on 2017/7/24.
 */

public class CountDownUtil {
    int mCurrent;
    int mTotal;
    Handler mHandler;
    long mStartTime;
    ICountDownListener mListener;
    CountDownUtil countDownUtil;
    private Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            int t= mTotal -mCurrent;
            if (mListener!=null){
                if (t>0){
                    mListener.onProgress(t,countDownUtil);
                    mCurrent++;
                    Log.i("cxmyDev","countDownTIme : "+t);
                    mHandler.postDelayed(countDownRunnable,1000);
                }else {
                    mListener.onDone();
                    long endTime = System.currentTimeMillis();
                    Log.i("cxmyDev","total time: "+(endTime-mStartTime));
                }
            }
        }
    };
    public CountDownUtil(int total,ICountDownListener listener){
        this.mTotal = total;
        this.mListener = listener;
        countDownUtil=this;
        mHandler = new Handler();
    }

    public void start(){
        mStartTime = System.currentTimeMillis();
        if (mListener!=null){
            mListener.onProgress(mTotal,countDownUtil);
            mCurrent++;
        }
        mHandler.postDelayed(countDownRunnable,1000);
    }

    public void stop(){
        mHandler.removeCallbacks(countDownRunnable);
    }

    public interface ICountDownListener{
        void onDone();
        void onProgress(int current,CountDownUtil util);
    }
}
