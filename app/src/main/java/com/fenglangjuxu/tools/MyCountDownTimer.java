package com.fenglangjuxu.tools;

import android.os.CountDownTimer;

public class MyCountDownTimer extends CountDownTimer {
    private OnCountDownListener onCountDownListener;

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (onCountDownListener != null){
            onCountDownListener.onTick(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (onCountDownListener != null){
            onCountDownListener.onFinish();
        }
    }

    public void setOnCountDownListener(OnCountDownListener onCountDownListener){
        this.onCountDownListener = onCountDownListener;
    }

    public interface OnCountDownListener{
        void onTick(long millisUntilFinished);
        void onFinish();
    }
}
