package com.fenglangjuxu.takeout.model.store;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.fenglangjuxu.takeout.event.TakeOutFoodNumChangedEvent;
import com.fenglangjuxu.view.NumberView;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.bus.RxBus;

/**
 * @author syj
 * @date 2019/6/27
 */
public class TakeOutCartPopItemMp extends ItemViewModel {
    private int tag;
    private int id;
    public ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<Integer> number = new ObservableField<>(0);
    public NumberView.OnNumberChangeListener numberChangeListener = new NumberView.OnNumberChangeListener() {
        @Override
        public void onNumberChanged(View view, int num, int difference) {
            if (difference == 1){
                number.set(number.get() + 1);
            }else {
                number.set(number.get() - 1);
            }
            RxBus.getDefault().post(new TakeOutFoodNumChangedEvent(id, tag, number.get(), difference, price.get(), name.get()));
        }
    };

    public TakeOutCartPopItemMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    public TakeOutCartPopItemMp(@NonNull BaseViewModel viewModel, int id, int tag, int number, String price, String name){
        super(viewModel);
        this.id = id;
        this.tag = tag;
        this.number.set(number);
        this.price.set(price);
        this.name.set(name);
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
