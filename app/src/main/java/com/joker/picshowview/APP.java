package com.joker.picshowview;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.joker.picshowview.gen.DaoMaster;
import com.joker.picshowview.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by aa on 2017/7/31.
 */

public class APP extends Application{
    public static final boolean ENCRYPTED = true;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mHelper = new DaoMaster.DevOpenHelper(this,"notes-db");
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        Fresco.initialize(this);

        Utils.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=5a1246ff");
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }
}
