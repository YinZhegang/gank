package io.gank.gank;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import io.gank.database.DaoMaster;
import io.gank.database.DaoSession;

/**
 * Created by yinzhegang on 2019/3/25.
 */

public class Resource extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"resource.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);

        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
