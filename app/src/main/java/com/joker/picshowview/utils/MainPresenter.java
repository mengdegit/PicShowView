package com.joker.picshowview.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by aa on 2017/8/8.
 */

public class MainPresenter {
    public final static int MSG_SHOW_TIPS = 0x01;

    private IMainView mMainView;

    private MainHandler mMainHandler;

    private boolean tipsIsShowed = true;

    public MainPresenter(IMainView view)
    {
        mMainView = view;
        mMainHandler = new MainHandler();
    }

    public class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_SHOW_TIPS:
                    mMainView.showTipsView();
                    tipsIsShowed =true;
                    break;
            }
        }
    }

    private Runnable tipsShowRunable = new Runnable() {
        @Override
        public void run() {
            mMainHandler.obtainMessage(MSG_SHOW_TIPS).sendToTarget();
        }
    };

    /**
     *
     * <无操作时开始计时>
     * <功能详细描述>
     *
     * @see [类、类#方法、类#成员]
     */
    public void startTipsTimer()
    {
        mMainHandler.postDelayed(tipsShowRunable, 5000);
    }

    /**
     *
     * <结束当前计时,重置计时>
     * <功能详细描述>
     *
     * @see [类、类#方法、类#成员]
     */
    public void endTipsTimer()
    {
        mMainHandler.removeCallbacks(tipsShowRunable);
    }

    public void resetTipsTimer()
    {
        tipsIsShowed = false;
        mMainHandler.removeCallbacks(tipsShowRunable);
        mMainHandler.postDelayed(tipsShowRunable, 5000);
    }

    public interface IMainView
    {
        public void showTipsView();
    }
}
