package com.joker.picshowview;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
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
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }
}
