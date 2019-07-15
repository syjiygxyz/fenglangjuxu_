package com.fenglangjuxu.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fenglangjuxu.R;
import com.fenglangjuxu.base.utils.CurrentActivityUtil;
import com.fenglangjuxu.databinding.PopTakeoutCartBinding;
import com.fenglangjuxu.takeout.model.store.TakeOutCartPopMp;

/**
 * @author syj
 * @date 2019/6/28
 */
public class TakeOutCartDialog extends Dialog {

    private PopTakeoutCartBinding binding;
    private TakeOutCartPopMp cartPopMp;

    public TakeOutCartDialog(@NonNull Context context, TakeOutCartPopMp cartMp) {
        super(context);
        this.cartPopMp = cartMp;
    }

    public TakeOutCartDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(CurrentActivityUtil.getCurrentActivity(), R.layout.pop_takeout_cart);
        binding.setCart(cartPopMp);
    }
}
