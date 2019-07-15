package com.fenglangjuxu;

import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.BaseApplication;

public class NnApplication extends BaseApplication {
    public static final boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
