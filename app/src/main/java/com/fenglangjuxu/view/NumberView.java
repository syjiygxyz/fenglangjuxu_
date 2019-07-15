package com.fenglangjuxu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fenglangjuxu.R;

/**
 * Created by wq on 2018/11/20.
 */
public class NumberView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private LinearLayout decrease;
    private LinearLayout increase;
    private int amount = 1;
    private int maxNumber = 99;
    private TextView tvNumber;
    private OnNumberChangeListener numberChangedListener;

    public NumberView(Context context) {
        this(context,null);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.number_view,this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberView);
        amount = typedArray.getInteger(R.styleable.NumberView_num,0);
        decrease = findViewById(R.id.ll_decrease);
        increase = findViewById(R.id.ll_increase);
        tvNumber = findViewById(R.id.tv_number);
        decrease.setOnClickListener(this);
        increase.setOnClickListener(this);
//        tvNumber.setOnClickListener(this);
        tvNumber.setText(String.valueOf(amount));
        tvNumber.addTextChangedListener(this);
        typedArray.recycle();
        setVisible();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_decrease:
                numberDecrease();
                if (numberChangedListener != null)
                numberChangedListener.onNumberChanged(v, amount, -1);
                break;
            case R.id.ll_increase:
                numberIncrease();
                if (numberChangedListener != null)
                numberChangedListener.onNumberChanged(v, amount, 1);
                break;
                default:
                    break;
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        tvNumber.setText(String.valueOf(amount));
        setVisible();
    }

    private void setVisible(){
        if (amount <= 0){
            tvNumber.setVisibility(INVISIBLE);
            decrease.setVisibility(INVISIBLE);
        }else {
            tvNumber.setVisibility(VISIBLE);
            decrease.setVisibility(VISIBLE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
//        if (numberChangedListener != null){
//            numberChangedListener.onNumberChanged(Integer.valueOf(s.toString()));
//        }
    }

    public void numberDecrease(){
        if (amount > 0){
            amount--;
            tvNumber.setText(String.valueOf(amount));
        }
        setVisible();
    }

    public void numberIncrease(){
        if (amount < maxNumber){
            amount++;
            tvNumber.setText(String.valueOf(amount));
        }
        setVisible();
    }

    public void numberChanged(int num){
        setAmount(num);
        tvNumber.setText(String.valueOf(num));
    }

    public void setEnable(boolean enable){
        tvNumber.setEnabled(enable);
        decrease.setEnabled(enable);
        increase.setEnabled(enable);
    }


    public void setOnNumberChangedListener(OnNumberChangeListener numberChangedListener){
        this.numberChangedListener = numberChangedListener;
    }

    public interface OnNumberChangeListener{
        void onNumberChanged(View view, int num, int difference);
    }
}
