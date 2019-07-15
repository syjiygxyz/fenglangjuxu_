package com.fenglangjuxu.model.main.social;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @author syj
 * @date 2019/6/17
 */
public class CommentItemMp extends ItemViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> content = new ObservableField<>("");
    public CommentItemMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    public CommentItemMp(@NonNull BaseViewModel viewModel, String name, String content){
        this(viewModel);
        this.name.set(name);
        this.content.set(content);
    }
}
