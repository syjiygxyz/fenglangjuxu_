package com.fenglangjuxu.takeout.model.store;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.fenglangjuxu.event.AddShoppingCartAnimatorEvent;
import com.fenglangjuxu.takeout.event.TakeOutFoodNumChangedEvent;
import com.fenglangjuxu.view.NumberView;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.bus.RxBus;

/**
 * @author syj
 * @date 2019/6/24
 */
public class TakeOutGoodItemMp extends ItemViewModel {
    private int tag;
    private int id;
    public ObservableField<String> price = new ObservableField<>("19.6");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<NumberView.OnNumberChangeListener> numberChangeListener = new ObservableField<>(new NumberView.OnNumberChangeListener() {
        @Override
        public void onNumberChanged(View view, int num, int difference) {
            if (difference > 0){
                int [] startLocation = new int[2];
                view.getLocationInWindow(startLocation);
                startLocation[1] += view.getHeight()/2;
                RxBus.getDefault().post(new AddShoppingCartAnimatorEvent(startLocation));
            }
            RxBus.getDefault().post(new TakeOutFoodNumChangedEvent(id, tag, num, difference, price.get(), name.get()));
        }
    });
    public ObservableField<Integer> number = new ObservableField<>(0);
    public TakeOutGoodItemMp(@NonNull BaseViewModel viewModel) {
        this(viewModel, "");
    }

    public TakeOutGoodItemMp(@NonNull BaseViewModel viewModel, String name){
        this(viewModel, name, 0, 0);
    }

    public TakeOutGoodItemMp(@NonNull BaseViewModel viewModel, String name, int tag, int id){
        super(viewModel);
        this.name.set(name);
        this.tag = tag;
        this.id = id;
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
