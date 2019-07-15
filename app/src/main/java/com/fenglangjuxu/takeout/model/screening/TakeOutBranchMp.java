package com.fenglangjuxu.takeout.model.screening;

import android.app.Application;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @author syj
 * @date 2019/7/4
 */
public class TakeOutBranchMp extends BaseViewModel {
    private int id;

    public TakeOutBranchMp(Application context) {
        super(context);
    }

    public TakeOutBranchMp(Application context, int id){
        super(context);
        this.id = id;
    }
}
