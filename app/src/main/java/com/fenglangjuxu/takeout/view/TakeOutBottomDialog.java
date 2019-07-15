package com.fenglangjuxu.takeout.view;

import android.app.Application;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.fenglangjuxu.R;
import com.fenglangjuxu.databinding.LayoutTakeoutBottomBinding;
import com.fenglangjuxu.takeout.model.store.TakeOutStoreBottomMp;

import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/6/26
 */
public class TakeOutBottomDialog extends DialogFragment {

    private LayoutTakeoutBottomBinding bottomLayoutBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setBackgroundDrawable(null);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//            attributes.windowAnimations = R.style.FragmentDialogAnimation;
            dialog.getWindow().setAttributes(attributes);
            dialog.setCanceledOnTouchOutside(false);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
       /* Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setBackgroundDrawable(null);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            attributes.windowAnimations = R.style.FragmentDialogAnimation;
            dialog.getWindow().setAttributes(attributes);
            dialog.setCanceledOnTouchOutside(false);

        }*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.layout_takeout_bottom, container, false);
        bottomLayoutBinding.setBottom(new TakeOutStoreBottomMp((Application) Utils.getContext()));
        return bottomLayoutBinding.getRoot();
    }
}
