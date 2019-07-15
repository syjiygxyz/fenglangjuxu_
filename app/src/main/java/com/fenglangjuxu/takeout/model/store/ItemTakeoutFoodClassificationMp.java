package com.fenglangjuxu.takeout.model.store;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.fenglangjuxu.takeout.event.TakeOutFoodClassificationSelectedEvent;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;

/**
 * @author syj
 * @date 2019/6/21
 */
public class ItemTakeoutFoodClassificationMp extends ItemViewModel<BaseViewModel> {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableBoolean selected = new ObservableBoolean(false);
    public ObservableField<Integer> pickNumber = new ObservableField<>(0);
    public ObservableField<String> pickNumberStr = new ObservableField<>("0");
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxBus.getDefault().post(new TakeOutFoodClassificationSelectedEvent(name.get()));
        }
    });

    public ItemTakeoutFoodClassificationMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    public ItemTakeoutFoodClassificationMp(@NonNull BaseViewModel viewModel, String name, boolean selected){
        super(viewModel);
        this.name.set(name);
        this.selected.set(selected);
    }
}
