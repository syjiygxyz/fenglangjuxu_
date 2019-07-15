package com.fenglangjuxu.takeout.model.store;

import android.app.Application;
import android.app.Dialog;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.PopupWindow;

import com.fenglangjuxu.databinding.LayoutTakeoutBottomBinding;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @author syj
 * @date 2019/6/26
 */
public class TakeOutStoreBottomMp extends BaseViewModel {
    private BottomClickListener listener;
    public PopupWindow popupWindow;
    public LayoutTakeoutBottomBinding bottomBinding;
    public ObservableField<String> totalNumStr = new ObservableField<>("0");
    public ObservableField<Integer> totalNum = new ObservableField<>(0);
    public ObservableField<Float> totalPrice = new ObservableField<>(0.00f);
    public ObservableField<String> totalPriceStr = new ObservableField<>("");
    public ObservableBoolean enable = new ObservableBoolean(false);
    public TakeOutStoreBottomMp(@NonNull Application application){
        super(application);
    }

    public BindingCommand goPay = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           if (listener != null){
               listener.goPay();
           }
        }
    });

    public BindingCommand cartClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (listener != null){
                listener.clickCart();
            }
        }
    });
    public void setPopupWindow(PopupWindow popupWindow){
        this.popupWindow = popupWindow;
    }

    public void setBottomClickListener(BottomClickListener listener){
        this.listener = listener;
    }

    public interface BottomClickListener{
        void clickCart();
        void goPay();
    }

    public void setBinding(LayoutTakeoutBottomBinding binding){
        this.bottomBinding = binding;
    }
}
