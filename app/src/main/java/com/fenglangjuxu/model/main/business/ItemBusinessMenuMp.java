package com.fenglangjuxu.model.main.business;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @author syj
 * @date 2019/6/11
 */
public class ItemBusinessMenuMp extends ItemViewModel {
    public ObservableField<String> path = new ObservableField<>("");
    public ObservableField<Drawable> image = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>("");
    public ItemBusinessMenuMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }
    public ItemBusinessMenuMp(@NonNull BaseViewModel viewModel, Drawable drawable, String name,@NonNull String path){
        super(viewModel);
        this.image.set(drawable);
        this.name.set(name);
        this.path.set(path);
    }

    public BindingCommand clickCommand = new BindingCommand(() -> {
        if (!path.get().isEmpty()){
            ARouter.getInstance().build("/takeout/home").navigation();
        }
    });
}
