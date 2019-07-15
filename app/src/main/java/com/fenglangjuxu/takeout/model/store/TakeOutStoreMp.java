package com.fenglangjuxu.takeout.model.store;

import android.app.Activity;
import android.app.Application;

import com.fenglangjuxu.animator.AddShopCartAnimatorManager;
import com.fenglangjuxu.base.utils.CurrentActivityUtil;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @author syj
 * @date 2019/6/20
 */
public class TakeOutStoreMp extends BaseViewModel {
    public TakeOutStoreMp(Application context) {
        super(context);
    }

    public BindingCommand back = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Activity currentActivity = CurrentActivityUtil.getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.finish();
            }
        }
    });
}
