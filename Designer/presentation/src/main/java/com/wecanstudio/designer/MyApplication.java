package com.wecanstudio.designer;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

/**
 * 应用入口，提供applicationContext、初始化第三方类库
 * Created by xdsjs on 2015/12/9.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private Activity mCurrentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApplication getContext() {
        return instance;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(@NonNull Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }
}
