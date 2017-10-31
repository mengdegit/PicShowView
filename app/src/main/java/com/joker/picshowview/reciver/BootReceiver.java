package com.joker.picshowview.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joker.picshowview.view.MainActivity;
import com.socks.library.KLog;

/**
 * Created by aa on 2017/10/23.
 */

public class BootReceiver extends BroadcastReceiver{
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            KLog.i("开机自启","触发广播接收器");
            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
        }
    }
}
