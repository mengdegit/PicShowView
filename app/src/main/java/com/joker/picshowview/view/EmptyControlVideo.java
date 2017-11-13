package com.joker.picshowview.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.joker.picshowview.R;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * 无任何控制ui的播放
 * Created by guoshuyu on 2017/8/6.
 */

public class EmptyControlVideo extends ListGSYVideoPlayer {

    public EmptyControlVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public EmptyControlVideo(Context context) {
        super(context);
    }

    public EmptyControlVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.empty_control_video;
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;

        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;

        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }

    @Override
    public void setLooping(boolean looping) {
        super.setLooping(looping);
    }

    @Override
    protected void touchDoubleUp() {
        //super.touchDoubleUp();
        //不需要双击暂停
    }

    @Override
    public void onAutoCompletion() {
        //重写播放完毕的方法，是位置再指向第一个，实现列表循环的方式
        if (mPlayPosition < (mUriList.size() - 1)) {
            mPlayPosition++;
        }else {
            mPlayPosition =0;
        }
        GSYVideoModel gsyVideoModel = mUriList.get(mPlayPosition);
        setUp(gsyVideoModel.getUrl(), mCache, mCachePath, gsyVideoModel.getTitle());
        if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
            mTitleTextView.setText(gsyVideoModel.getTitle());
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                startPlayLogic();
            }
        }, 2000);
        return;
    }
}
