package com.fenglangjuxu.takeout.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.fenglangjuxu.R;
import com.fenglangjuxu.base.utils.CurrentActivityUtil;
import com.fenglangjuxu.databinding.LayoutTakeoutBottomBinding;
import com.fenglangjuxu.databinding.PopTakeoutCartBinding;
import com.fenglangjuxu.takeout.model.store.TakeOutCartPopMp;
import com.fenglangjuxu.takeout.model.store.TakeOutStoreBottomMp;

/**
 * @author syj
 * @date 2019/6/26
 */
public class TakeOutStoreWindow {
    public static PopupWindow createBottomPopWindow(Context context, TakeOutStoreBottomMp popMp){
        LayoutTakeoutBottomBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_takeout_bottom, null, false);
        View view = binding.getRoot();
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        binding.setBottom(popMp);
        popMp.setBinding(binding);
        popupWindow.setFocusable(false);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(false);
        popMp.setPopupWindow(popupWindow);

        return popupWindow;
    }

    public static PopupWindow createCartListPopWindow(Context context, TakeOutCartPopMp popMp){
        PopTakeoutCartBinding cartBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.pop_takeout_cart, null, false);
        View view = cartBinding.getRoot();
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        cartBinding.setCart(popMp);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popMp.setPopupWindow(popupWindow);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = CurrentActivityUtil.getCurrentActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                CurrentActivityUtil.getCurrentActivity().getWindow().setAttributes(attributes);
            }
        });
        return popupWindow;
    }
}
