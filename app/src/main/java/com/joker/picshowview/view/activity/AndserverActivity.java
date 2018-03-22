package com.joker.picshowview.view.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.joker.picshowview.R;
import com.joker.picshowview.server.NetBroadcastReceiver;
import com.joker.picshowview.server.ServerManager;

public class AndserverActivity extends AppCompatActivity {

    public NetBroadcastReceiver netBroadcastReceiver ;
    private ServerManager mServerManager;
    public Handler startHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://开启微服务
                    mServerManager.startService();
                    break;
                case 2://关闭微服务
                    mServerManager.stopService();
                    break;
                case 3://服务状态的回调
                    String message = (String) msg.obj;
                    if (message !=null){
                        Log.i("andserver",message);
                    }else {
                        Log.i("andserver","server closed");
                    }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andserver);
        //初始化广播接收器
        initBroadcastReceiver();



    }

    private void initBroadcastReceiver() {
        //注册网络监听
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        netBroadcastReceiver = new NetBroadcastReceiver(startHandler);
        registerReceiver(netBroadcastReceiver, filter);
        //注册微服务状态监听
        mServerManager = new ServerManager(this,startHandler);
        mServerManager.register();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mServerManager.unRegister();
        mServerManager.stopService();
        unregisterReceiver(netBroadcastReceiver);
    }
}
