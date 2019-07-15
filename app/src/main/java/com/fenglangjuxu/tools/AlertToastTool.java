package com.fenglangjuxu.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fenglangjuxu.R;

import java.lang.ref.WeakReference;

import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/6/5
 */
public final class AlertToastTool {
    private static WeakReference<View> sViewWeakReference;

    public static void show(String text){
        LayoutInflater inflater = (LayoutInflater) Utils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sViewWeakReference = new WeakReference<View>(inflater.inflate(R.layout.alert_toast_layout,null));
        TextView textView = sViewWeakReference.get().findViewById(R.id.tv_text);
        textView.setText(text);
        Toast toast = new Toast(Utils.getContext());
        toast.setView(sViewWeakReference.get());
        toast.setGravity(Gravity.CENTER,0 , 0);
        toast.show();
    }
}
