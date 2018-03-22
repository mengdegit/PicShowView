package com.joker.picshowview.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * Created by aa on 2018/3/21.
 */

public class NetBroadcastReceiver extends BroadcastReceiver{
    private Handler startHandler;

    public NetBroadcastReceiver(Handler handler){
        this.startHandler =handler;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            NetworkUtils.NetworkType netWorkState = NetworkUtils.getNetworkType();
            Log.i("andserver",netWorkState+"");
            switch (NetworkUtils.getNetworkType()){
                case NETWORK_UNKNOWN://以太网
                    Message message = new Message();
                    message.what = 1;
                    startHandler.sendMessage(message);
                    break;
                case NETWORK_4G://
                    break;
                case NETWORK_2G://
                    break;
                case NETWORK_3G://
                    break;
                case NETWORK_WIFI://
                    break;
                case NETWORK_NO://
                    Message message2 = new Message();
                    message2.what = 2;
                    startHandler.sendMessage(message2);
                    break;
                default:
                    break;
            }
        }
    }
}
