package com.fenglangjuxu.model;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.fenglangjuxu.apiServices.UserApi;
import com.fenglangjuxu.base.client.ApiCallBack;
import com.fenglangjuxu.base.client.ApiResult;
import com.fenglangjuxu.base.client.RetrofitClient;
import com.fenglangjuxu.tools.MyCountDownTimer;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class LoginMp extends BaseViewModel {

    public ObservableBoolean byPwd = new ObservableBoolean(false);
    public ObservableBoolean sendCodeEnable = new ObservableBoolean(true);
    public ObservableField<String> sendCodeText = new ObservableField<>("获取验证码");
    public ObservableField<String> mobile = new ObservableField<>("");
    public ObservableField<String> eCode = new ObservableField<>("");
    public ObservableBoolean loginEnable = new ObservableBoolean(false);
    public ObservableBoolean mobileLegal = new ObservableBoolean(false);
    public ObservableBoolean eCodeLegal = new ObservableBoolean(false);
    private MyCountDownTimer countDownTimer;


    public LoginMp(@NonNull Application application) {
        super(application);
        initView();
        initData();
    }

    private void initData() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Observable<ApiResult<String>> login = userApi.loginWithPassword("","");
        RetrofitClient.execute(login, new ApiCallBack<String>() {
            @Override
            protected void success(String data) {

            }

            @Override
            protected void error(int errorCode, String message) {

            }
        });
    }

    public LoginMp(@NonNull Application application, BaseModel model) {
        super(application, model);
    }



    private void initView(){
        countDownTimer = new MyCountDownTimer(60 * 1000, 100);
        countDownTimer.setOnCountDownListener(countDownListener);

    }


    private MyCountDownTimer.OnCountDownListener countDownListener = new MyCountDownTimer.OnCountDownListener() {
        @Override
        public void onTick(long millisUntilFinished) {
            sendCodeText.set(millisUntilFinished + "s");
        }

        @Override
        public void onFinish() {
            sendCodeText.set("获取验证码");
            sendCodeEnable.set(true);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
