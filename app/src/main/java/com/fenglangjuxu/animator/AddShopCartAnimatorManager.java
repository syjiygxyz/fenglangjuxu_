package com.fenglangjuxu.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.fenglangjuxu.R;
import com.fenglangjuxu.base.utils.ScreenUtils;


/**
 * Created by wq on 2018/12/18.
 */

public class AddShopCartAnimatorManager {
    private Context context;
    private ImageView cartView;
    private ViewGroup parent;
    private float[] currentPosition = new float[2];
    private AnimatorFinishListener listener;
    private ObjectAnimator objectAnimatorX;
    private ObjectAnimator objectAnimatorY;
    private ValueAnimator valueAnimator;

    public AddShopCartAnimatorManager(Context context, ViewGroup parent, ImageView imageView){
        this.context = context;
        this.parent = parent;
        this.cartView = imageView;
    }

    public void startAddShopAnimator(int[] startLocation){
        if (startLocation == null){
            playScaleAnimator();
            if (listener != null){
                listener.onFinish();
            }
            return;
        }
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) ScreenUtils.dp2px(context,10),
                (int)ScreenUtils.dp2px(context,10));
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.theme_color_oval_alpha));
        parent.addView(imageView,layoutParams);


        int[] endLocation = new int[2];
//        cartView.getLocationInWindow(endLocation);
        endLocation[0] = 90;
        endLocation[1] = ScreenUtils.getScreenHeight(context);
        int[] parentLocation = new int[2];
        parent.getLocationInWindow(parentLocation);

        int fromX = startLocation[0] - parentLocation[0];
        int fromY = startLocation[1] - parentLocation[1];

        int toX = endLocation[0] - parentLocation[0] + cartView.getWidth()/2;
        int toY = endLocation[1] - parentLocation[1] - cartView.getHeight()/2;

        Path path = new Path();
        path.moveTo(fromX,fromY);
        path.quadTo((fromX + toX)/2,fromY,toX,toY);
        PathMeasure pathMeasure = new PathMeasure(path,false);
        valueAnimator = ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animationValue = (float)animation.getAnimatedValue();
                pathMeasure.getPosTan(animationValue,currentPosition,null);
                imageView.setTranslationX(currentPosition[0]);
                imageView.setTranslationY(currentPosition[1]);
                Log.i("imageMove=======","x:" + currentPosition[0] + "y:" + currentPosition[1]);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                parent.removeView(imageView);
                playScaleAnimator();
                if (listener != null){
                    listener.onFinish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    private void playScaleAnimator(){
        objectAnimatorX = new ObjectAnimator().ofFloat(cartView,"scaleX",1.0f,1.2f,1.0f);
        objectAnimatorY = new ObjectAnimator().ofFloat(cartView,"scaleY",1.0f,1.2f,1.0f);
        objectAnimatorX.setInterpolator(new AccelerateInterpolator());
        objectAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimatorX).with(objectAnimatorY);
        set.setDuration(300);
        set.start();
    }

    public void addAnimatorFinishListener(AnimatorFinishListener listener){
        this.listener = listener;
    }

    public void onDestroy(){
        if (valueAnimator != null){
            valueAnimator.cancel();
            valueAnimator = null;
        }
        if (objectAnimatorX != null){
            objectAnimatorX.cancel();
            objectAnimatorX = null;
        }
        if (objectAnimatorY != null){
            objectAnimatorY.cancel();
            objectAnimatorY = null;
        }
    }

    public interface AnimatorFinishListener{
        void onFinish();
    }
}
