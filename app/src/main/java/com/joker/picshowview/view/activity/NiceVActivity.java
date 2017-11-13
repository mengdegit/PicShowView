package com.joker.picshowview.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.joker.picshowview.R;
import com.joker.picshowview.view.EmptyControlVideo;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NiceVActivity extends Activity {


    @BindView(R.id.video_player)
    EmptyControlVideo videoPlayer;

    List<GSYVideoModel> lists;
    @BindView(R.id.reload)
    Button reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nice_v);
        ButterKnife.bind(this);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer.setLooping(false);
                videoPlayer.release();
                videoPlayer.setUp(lists,true,0);
                videoPlayer.startPlayLogic();
            }
        });

        videoPlayer.setVideoAllCallBack(new VideoAllCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {

            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                Log.i("=======++", url);
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {
                Log.i("=======++", url);
                for (int i = 0; i < lists.size(); i++) {
                    if (url.equals(lists.get(i).getUrl())) {
                        lists.remove(i);
                    }
                }
                if (lists.size() > 0) {
                    videoPlayer.release();
//                    videoPlayer.setLooping(true);
                    videoPlayer.setUp(lists, true, 0);
//                    videoPlayer.startPlayLogic();
                }
            }

            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }
        });
        String url = "http://baobab.wdjcdn.com/145649580.mp4";
        lists = new ArrayList<>();
        lists.add(new GSYVideoModel("http://172.16.12.234:8097/app/20150801.mp4", ""));
        lists.add(new GSYVideoModel("http://baobab.wdjcdn.com/14564406580.mp4", ""));
        lists.add(new GSYVideoModel("http://172.16.12.234:8097/app/20150622雪肌.mp4", ""));
        lists.add(new GSYVideoModel("http://172.16.12.234:8097/app/20150710小鸡拉面.mp4", ""));
//        videoPlayer.setLooping(true);
        videoPlayer.setUp(lists, true, 0);
        videoPlayer.startPlayLogic();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
    }
}
