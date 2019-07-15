package com.fenglangjuxu.model.main;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.fenglangjuxu.databinding.ActivityMainBinding;
import com.fenglangjuxu.tools.AlertToastTool;
import com.gyf.immersionbar.ImmersionBar;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class MainMp extends BaseViewModel {
    private ActivityMainBinding binding;
    public ObservableField<Integer> tabPosition = new ObservableField<>(0);


    public MainMp(@NonNull Application application, ActivityMainBinding binding) {
        super(application);
        this.binding = binding;
    }

    public BindingCommand businessClick = new BindingCommand(() -> {
        tabPosition.set(0);
        binding.mainPager.setCurrentItem(0);
    });

    public BindingCommand socialClick = new BindingCommand(() -> {
        tabPosition.set(1);
        binding.mainPager.setCurrentItem(1);
    });

    public BindingCommand amusementClick = new BindingCommand(() -> {
        tabPosition.set(2);
        binding.mainPager.setCurrentItem(2);
    });

    public BindingCommand mineClick = new BindingCommand(() -> {
        tabPosition.set(3);
        binding.mainPager.setCurrentItem(3);
    });

    public BindingCommand publishClick = new BindingCommand(() -> AlertToastTool.show("发布点啥"));

}
