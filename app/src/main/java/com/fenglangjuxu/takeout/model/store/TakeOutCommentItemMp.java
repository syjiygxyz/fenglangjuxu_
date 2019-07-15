package com.fenglangjuxu.takeout.model.store;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.model.main.social.ImageItemMp;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author syj
 * @date 2019/7/3
 */
public class TakeOutCommentItemMp extends BaseViewModel {
    public ObservableList<ImageItemMp> images = new ObservableArrayList<>();
    public final BindingRecyclerViewAdapter imageAdapter = new BindingRecyclerViewAdapter();
    public ItemBinding itemBinding = ItemBinding.of(BR.item, R.layout.item_iamge);
    public TakeOutCommentItemMp(@NonNull Application context) {
        super(context);
        for (int i = 0; i < 3; i++){
            images.add(new ImageItemMp(this));
        }
    }
}
