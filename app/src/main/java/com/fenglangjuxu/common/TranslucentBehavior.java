package com.fenglangjuxu.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.immersionbar.ImmersionBar;

/**
 * @author syj
 * @date 2019/6/20
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<View> {
    private float mToolbarHeight;
    private boolean darkFont = false;
    private Context context;

    public TranslucentBehavior() {
    }

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof LinearLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
       if (dependency.getY() > 0){
           if (mToolbarHeight == 0) {
               mToolbarHeight = dependency.getY() - child.getHeight();
           }

           float dy = dependency.getY() - child.getHeight();
           dy = dy < 0 ? 0 : dy;
           float alpha = 1 - (dy / mToolbarHeight);
//           Log.d("BEHAVIOR=====","alpha--->"+ alpha + "||dy" + dy);
           child.setAlpha(alpha);

           if (!darkFont && alpha >= 0.5){
               ImmersionBar.with((Activity)context)
                       .statusBarDarkFont(true, 0.5f)
                       .init();
               darkFont = true;
           }else if (darkFont && alpha < 0.5){
               ImmersionBar.with((Activity)context)
                       .statusBarDarkFont(false, 0.5f)
                       .init();
               darkFont = false;
           }
       }

        return true;
    }
}
