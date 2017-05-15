package com.example.hoang.truyenratngan;

import android.app.Application;
import android.util.Log;

import com.example.hoang.truyenratngan.databases.StoryDatabase;
import com.example.hoang.truyenratngan.databases.models.DaoMaster;
import com.example.hoang.truyenratngan.databases.models.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * Created by Hoang on 4/18/2017.
 */

public class StoryApplication extends Application {
    private static final String TAG = "StoryApplication";
    private StoryDatabase storyDatabase;
    private DaoMaster.DevOpenHelper daoHelper;

    private static StoryApplication instance;

    @Override
    public void onCreate() {
        storyDatabase = new StoryDatabase(this);
        instance = this;
        daoHelper = new DaoMaster.DevOpenHelper(this, "story1.db");
        super.onCreate();
    }

    public StoryDatabase getStoryDatabase() {
        return storyDatabase;
    }

    public static StoryApplication getInstance() {
        return instance;
    }

    public DaoMaster.DevOpenHelper getDaoHelper() {
        return daoHelper;
    }
}
