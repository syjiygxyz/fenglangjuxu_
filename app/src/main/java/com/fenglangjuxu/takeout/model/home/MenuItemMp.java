package com.fenglangjuxu.takeout.model.home;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @author syj
 * @date 2019/6/19
 */
public class MenuItemMp extends ItemViewModel {
    public ObservableField<Integer> id = new ObservableField<>(0);
    public ObservableField<String> path = new ObservableField<>("");
    public ObservableField<Drawable> image = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>("");
    public MenuItemMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }
    public MenuItemMp(@NonNull BaseViewModel viewModel, Drawable drawable, String name,@NonNull String path, int id){
        super(viewModel);
        this.image.set(drawable);
        this.name.set(name);
        this.path.set(path);
        this.id.set(id);
    }

    public BindingCommand clickCommand = new BindingCommand(() -> {
        if (!path.get().isEmpty()){
            ARouter.getInstance().build(path.get())
                    .withString("name", name.get())
                    .withInt("id", id.get())
                    .navigation();
        }
    });
}
