package com.fenglangjuxu.takeout.model.home;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @author syj
 * @date 2019/6/19
 */
public class TakeOutItemMp extends ItemViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public TakeOutItemMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }
    public TakeOutItemMp(@NonNull BaseViewModel viewModel, String name) {
        super(viewModel);
        this.name.set(name);
    }
    public BindingCommand itemCLick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build("/takeout/store").navigation();
        }
    });
}
